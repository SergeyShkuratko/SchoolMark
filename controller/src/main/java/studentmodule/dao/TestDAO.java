package studentmodule.dao;

import studentmodule.dao.dto.TestDTO;
import studentmodule.dao.dto.TestsDTO;
import studentmodule.dao.dto.WorkPageDTO;

import java.util.List;

public interface TestDAO {
    List<TestsDTO> getTestsForVerifier(int verifierId);

    TestDTO getTestInfoAndWorkIdsByTestId(int testId);

    WorkPageDTO getWorkPagesByWorkId(int workId);
}
