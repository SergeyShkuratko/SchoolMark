package controller.dao;

import controller.dao.dto.TestDTO;
import controller.dao.dto.TestsDTO;
import controller.dao.dto.WorkPageDTO;

import java.util.List;

public interface TestDAO {
    List<TestsDTO> getTestsForVerifier(int verifierId);

    TestDTO getTestInfoAndWorkIdsByTestId(int testId);

    WorkPageDTO getWorkPagesByWorkId(int workId);

    boolean setWorkStatusVerified(int workId);
}
