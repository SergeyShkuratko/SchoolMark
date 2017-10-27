package template.services;

import org.springframework.stereotype.Service;
import template.dto.TestQuestion;
import template.dto.TestVariant;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class TestTemplateService {

    public List<TestVariant> getTestVariantsFromReq(HttpServletRequest req) {
        Map<String, TestVariant> testVariants = new TreeMap<>();
        Map<String, TestQuestion> testQuestions = new TreeMap<>();

        for (Map.Entry<String, String[]> param : req.getParameterMap().entrySet()) {
            if(!param.getKey().contains("question"))
                continue;
            String[] paramTypes = param.getKey().split("_");
            String variant = paramTypes[0];
            TestVariant testVariant;
            String question = variant + paramTypes[1];
            TestQuestion testQuestion;
            switch (paramTypes[2]) { //проверяем 3-ую часть параметра
                case "text":
                    testVariant = getTestVariant(testVariants, variant);
                    testQuestion = getTestQuestion(testQuestions, question, testVariant);
                    testQuestion.setQuestionText(param.getValue()[0]);
                    break;
                case "answer":
                    testVariant = getTestVariant(testVariants, variant);
                    testQuestion = getTestQuestion(testQuestions, question, testVariant);
                    testQuestion.setAnswerText(param.getValue()[0]);
                    break;
                default: //если попали сюда, значит парметр - критерий (может быть criterion1, criterion2...)
                    testVariant = getTestVariant(testVariants, variant);
                    testQuestion = getTestQuestion(testQuestions, question, testVariant);
                    testQuestion.getCriterians().add(param.getValue()[0]);
                    break;
            }
        }
        return new ArrayList<>(testVariants.values());
    }

    private TestQuestion getTestQuestion(Map<String, TestQuestion> testQuestions, String question, TestVariant testVariant) {
        TestQuestion testQuestion;
        if (testQuestions.containsKey(question)){
            testQuestion = testQuestions.get(question);
        }
        else {
            testQuestion = new TestQuestion();
            testQuestions.put(question, testQuestion);
            testVariant.getTestQuestions().add(testQuestion);
        }
        return testQuestion;
    }

    private TestVariant getTestVariant(Map<String, TestVariant> testVariants, String variant) {
        TestVariant testVariant;
        if (testVariants.containsKey(variant)) {
            testVariant = testVariants.get(variant);
        } else {
            testVariant = new TestVariant();
            testVariant.setVariant("Вариант №" + variant.replace("variant",""));
            testVariants.put(variant, testVariant);
        }
        return testVariant;
    }
}