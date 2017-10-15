package template.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestQuestion {
    private String questionText;
    private String answerText;
    private List<String> criterians;

    public TestQuestion() {
        this.criterians = new ArrayList<>();
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public List<String> getCriterians() {
        return criterians;
    }

    public void setCriterians(List<String> criterians) {
        this.criterians = criterians;
    }
}
