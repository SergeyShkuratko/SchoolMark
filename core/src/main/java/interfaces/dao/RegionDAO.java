package interfaces.dao;

import classes.Region;
import exceptions.RegionDAOException;

import java.util.List;

public interface RegionDAO {

    Region getById(int id) throws RegionDAOException;
    Region insert(Region region) throws RegionDAOException;
    List<Region> getAll() throws RegionDAOException;
}
