package dao;

import dao.dto.TestDTO;
import dao.dto.TestsDTO;
import dao.dto.WorkPageDTO;

import java.util.List;

public interface TestDAO {
    List<TestsDTO> getTestsForVerifier(int verifierId);

    TestDTO getTestInfoAndWorkIdsByTestId(int testId);

    WorkPageDTO getWorkPagesByWorkId(int workId);
}
