package student.dao;

import classes.Region;
import connectionmanager.ConnectionPool;
import connectionmanager.TomcatConnectionPool;
import exceptions.RegionDAOException;
import interfaces.dao.RegionDAO;
import org.apache.log4j.Logger;
import utils.dao.DAOUtilsRegion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static core.dao.constants.DAOConstants.NULL_POINTER_DB;

public class RegionDAOImpl implements RegionDAO {

    private static final Logger logger = Logger.getLogger(RegionDAOImpl.class);
    private static final ConnectionPool pool = TomcatConnectionPool.getInstance();
    private static final String GET_BY_ID = "SELECT * FROM region WHERE id = ?";
    private final String baseGetSql = "SELECT * FROM region ";
    private final String insertSql = "INSERT INTO region (num, name) VALUES (?, ?)";
    private final String updateSql = "UPDATE region " +
            "SET num = ?, name = ? " +
            "WHERE id = ?";
    private final String deleteSql = "DELETE FROM region WHERE id = ?";

    @Override
    public Region getById(int id) throws RegionDAOException {
        Region result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            List<Region> regions = regionFromResultSet(set);
            if (!regions.isEmpty()) {
                result = regions.get(0);
            }
        } catch (SQLException | RegionDAOException e) {
            logger.error(e.getMessage());
            logger.debug(e);
            throw new RegionDAOException();
        }
        return result;
    }

    @Override
    public List<Region> getAll() throws RegionDAOException {
        return this.getMany("");
    }

    private List<Region> regionFromResultSet(ResultSet set) throws RegionDAOException {
        List<Region> result = new ArrayList<>();

        try {
            while (set.next()) {
                result.add(new Region(
                        set.getInt("id"),
                        set.getInt("num"),
                        set.getString("name")));
            }
        } catch (SQLException e) {
            throw new RegionDAOException(e.fillInStackTrace());
        }
        return result;
    }

    private Region getOne(String where) throws RegionDAOException {
        try(
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(baseGetSql + where)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return DAOUtilsRegion.getRegionByResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RegionDAOException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new RegionDAOException(npe);
        }
    }

    private List<Region> getMany(String where) throws RegionDAOException {
        try(
                Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(baseGetSql + where)) {
            ResultSet rs = statement.executeQuery();
            ArrayList<Region> students = new ArrayList<>();
            while (rs.next()) {
                students.add(DAOUtilsRegion.getRegionByResultSet(rs));
            }
            return students.size() != 0 ? students : null;
        } catch (SQLException e) {
            throw new RegionDAOException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new RegionDAOException(npe);
        }
    }

    private void insertOne(Region region, PreparedStatement preparedStatement)
            throws RegionDAOException {
        try {
            preparedStatement.setInt(1, region.getNum());
            preparedStatement.setString(2, region.getName());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new RegionDAOException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new RegionDAOException(npe.getMessage());
        }
    }

    private void checkInDBaseAndException(Region region)
            throws RegionDAOException {
        if(region.getId() != NULL_POINTER_DB)
            throw new RegionDAOException (
                    "You can't insert object into db which is already there !!!");
    }

    private void checkDoesNotInDBaseAndException(Region region)
            throws RegionDAOException {
        if(region.getId() == NULL_POINTER_DB)
            throw new RegionDAOException (
                    "You can't update object into db which have not there yet !!!");
    }

    @Override
    public int insertRegion(Region region) throws RegionDAOException {
        this.checkInDBaseAndException(region);
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            this.insertOne(region, preparedStatement);
            preparedStatement.executeBatch();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
            return NULL_POINTER_DB;
        } catch (SQLException e) {
            throw new RegionDAOException(e.getMessage());
        }
    }

    @Override
    public int removeRegion(Region region) throws RegionDAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(deleteSql)) {
            if(region.getId() == NULL_POINTER_DB) {
                throw new RegionDAOException("Region is not already exists !!!");
            }
            preparedStatement.setInt(1, region.getId());
            preparedStatement.addBatch();
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new RegionDAOException(
                    "Exception on delete row with id = " +
                            region.getId() + "(" + e.getMessage() + ")");
        } catch (NullPointerException npe) {
            throw new RegionDAOException("input Region has null value (" + npe.getMessage() + ")");
        }
    }

    @Override
    public int insertRegions(List<Region> regions)
            throws RegionDAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(insertSql)) {
            for(Region region : regions) {
                this.checkInDBaseAndException(region);
                this.insertOne(region, preparedStatement);
            }
            return preparedStatement.executeBatch().length;
        } catch (SQLException e) {
            throw new RegionDAOException(e.getMessage());
        }
    }

    @Override
    public int updateRegion(Region region)
            throws RegionDAOException {
        this.checkDoesNotInDBaseAndException(region);
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setInt(2, region.getNum());
            preparedStatement.setString(3, region.getName());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RegionDAOException(e.getMessage());
        }
    }

}