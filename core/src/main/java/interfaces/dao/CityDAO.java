package interfaces.dao;

import classes.City;
import classes.Region;
import classes.dto.CityDTO;
import exceptions.CityDAOException;
import exceptions.RegionDAOException;

import java.util.List;

public interface CityDAO {

    City getById(int id) throws CityDAOException;
    List<City> getByRegion(int regionId) throws CityDAOException;
    List<City> getAllWithRegions() throws CityDAOException;
    int insertCity(City city) throws CityDAOException;
    int removeCity(City city) throws CityDAOException;
    int insertCities(List<City> cities) throws CityDAOException;
    int updateCity(City city) throws CityDAOException;
    List<City> getAll() throws CityDAOException;
    CityDTO getCityDtoById(int cityId) throws CityDAOException;
}
