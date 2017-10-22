package interfaces.dao;

import classes.City;
import exceptions.CityDAOException;

import java.util.List;

public interface CityDAO {

    City getById(int id) throws CityDAOException;
    List<City> getByRegion(int regionId) throws CityDAOException;
    List<City> getAllWithRegions() throws CityDAOException;
    City insert(City city) throws CityDAOException;
}
