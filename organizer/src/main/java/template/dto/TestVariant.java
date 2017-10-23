package template.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkm on 15.10.2017.
 */
public class TestVariant {
    private int id;
    private String variant;
    private List<TestQuestion> testQuestions;

    public TestVariant() {
        this.testQuestions = new ArrayList<>();
    }

    public TestVariant(int id, String variant) {
        this.testQuestions = new ArrayList<>();
        this.id = id;
        this.variant = variant;
    }

    public boolean sameAs(TestVariant that){
        if(!variant.equals(that.getVariant())){
            return false;
        }
        if(testQuestions.size() != that.getTestQuestions().size()){
            return false;
        }
        for (int i = 0; i < testQuestions.size(); i++) {
             if(!testQuestions.get(i).sameAs(that.getTestQuestions().get(i))){
                 return false;
             }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public List<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }
}
