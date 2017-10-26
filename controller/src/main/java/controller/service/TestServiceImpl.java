package controller.service;

import controller.dao.dto.TestDTO;
import controller.dao.dto.TestsDTO;
import controller.dao.dto.WorkPageDTO;
import controller.dao.TestDAO;
import controller.dao.daoimplementation.TestDAOImpl;
import controller.jsonconverter.Converter;
import controller.jsonconverter.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    TestDAO testDAO;
    Converter converter;

    public TestServiceImpl(TestDAO testDAO, Converter converter) {
        this.testDAO = testDAO;
        this.converter = converter;
    }

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
