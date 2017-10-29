package services;

import classes.dto.CityDTO;
import exceptions.CityDAOException;

import java.util.Map;

public interface CityService {
    Map<String, String> insertNewRecord(String cityName, Integer regionNum);
    Map<String, String> removeRecordById(Integer cityId);
    CityDTO getCityDtoById(int id) throws CityDAOException;
}
