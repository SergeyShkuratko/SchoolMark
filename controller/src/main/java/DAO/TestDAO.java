package DAO;

import DAO.DTO.TestDTO;
import DAO.DTO.TestsDTO;
import DAO.DTO.WorkPageDTO;

import java.util.List;

public interface TestDAO {
    List<TestsDTO> getTestsForVerifier(int verifierId);

    TestDTO getTestInfoAndWorkIdsByTestId(int testId);

    WorkPageDTO getWorkPagesByWorkId(int workId);
}
