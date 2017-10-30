package student.dao;

import student.dao.dto.TestDTO;
import student.dao.dto.TestsDTO;
import student.dao.dto.WorkPageDTO;

import java.util.List;

public interface TestDAO {
    List<TestsDTO> getTestsForVerifier(int verifierId);

    TestDTO getTestInfoAndWorkIdsByTestId(int testId);

    WorkPageDTO getWorkPagesByWorkId(int workId);
}
