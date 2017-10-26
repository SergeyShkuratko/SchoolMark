package services;

import java.util.Map;

public interface RegionService {
    Map<String, String> insertNewRecord(String regionName, Integer regionNum);

    Map<String, String> removeRecordById(Integer regionId);
}
