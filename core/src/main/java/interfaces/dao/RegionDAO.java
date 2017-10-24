package interfaces.dao;

import classes.Region;
import exceptions.RegionDAOException;

import java.util.List;

public interface RegionDAO {

    Region getById(int id) throws RegionDAOException;
    int insertRegion(Region region) throws RegionDAOException;
    int removeRegion(Region region) throws RegionDAOException;
    int insertRegions(List<Region> regions) throws RegionDAOException;
    int updateRegion(Region region) throws RegionDAOException;
    List<Region> getAll() throws RegionDAOException;
}
