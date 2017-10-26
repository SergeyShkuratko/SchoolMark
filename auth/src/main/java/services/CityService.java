package services;

import classes.dto.CityDTO;
import exceptions.CityDAOException;

public interface CityService {
    CityDTO getCityDtoById(int id) throws CityDAOException;
}
