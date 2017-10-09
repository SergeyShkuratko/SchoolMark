package classes;

public class Question {
    private final int id;
    private final int id_sample;
    private String question;
    private String answer;
    private String criterion;

    public Question(int id, int id_sample, String question, String answer, String criterion) {
        this.id = id;
        this.id_sample = id_sample;
        this.question = question;
        this.answer = answer;
        this.criterion = criterion;
    }

    public int getId() {
        return id;
    }

    public int getId_sample() {
        return id_sample;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }
}
