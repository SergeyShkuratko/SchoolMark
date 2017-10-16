package service;

import DAO.DTO.TestsDTO;

import java.util.List;

public interface TestService {
    List<TestsDTO> getWorksForVerifier(int verifierId);

    String getTestInfoJsonByTestId(int testId);

    String getWorkPagesJsonByWorkId(int workId);
}
