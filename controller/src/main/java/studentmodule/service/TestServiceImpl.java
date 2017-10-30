package studentmodule.service;

import studentmodule.dao.dto.TestDTO;
import studentmodule.dao.dto.TestsDTO;
import studentmodule.dao.dto.WorkPageDTO;
import studentmodule.dao.TestDAO;
import studentmodule.dao.daoimplementation.TestDAOImpl;
import jsonconverter.Converter;
import jsonconverter.JsonConverter;

import java.util.List;

public class TestServiceImpl implements TestService {
    TestDAO testDAO = new TestDAOImpl();
    Converter converter = new JsonConverter();

    @Override
    public List<TestsDTO> getWorksForVerifier(int verifierId) {
        return testDAO.getTestsForVerifier(verifierId);
    }

    @Override
    public String getTestInfoJsonByTestId(int testId) {
        TestDTO testInfoByTestId = testDAO.getTestInfoAndWorkIdsByTestId(testId);
        String convert = converter.convert(testInfoByTestId);
        if ("null".equals(convert)) {
            return "";
        }
        return convert;
    }

    @Override
    public String getWorkPagesJsonByWorkId(int workId) {
        WorkPageDTO workPagesByWorkId = testDAO.getWorkPagesByWorkId(workId);
        String convert = converter.convert(workPagesByWorkId);
        if ("null".equals(convert)) {
            return "";
        }
        return convert;
    }
}
