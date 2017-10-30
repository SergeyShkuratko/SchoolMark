package studentmodule.service;

import studentmodule.dao.dto.TestsDTO;

import java.util.List;

public interface TestService {
    List<TestsDTO> getWorksForVerifier(int verifierId);

    String getTestInfoJsonByTestId(int testId);

    String getWorkPagesJsonByWorkId(int workId);
}
