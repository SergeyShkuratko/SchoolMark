package services;

import java.util.Map;

public interface CityService {
    Map<String, String> insertNewRecord(String cityName, Integer regionNum);
    Map<String, String> removeRecordById(Integer cityId);
}
