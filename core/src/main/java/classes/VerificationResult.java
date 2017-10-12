package classes;

import java.time.LocalDate;

public class VerificationResult {
    private final int id;
    private final Work work;
    private final Teacher verifier;
    private LocalDate verificationDate;
    private String comment;
    private int mark;

    public VerificationResult(int id, Work work, Teacher verifier) {
        this.id = id;
        this.work = work;
        this.verifier = verifier;
    }

    public int getId() {
        return id;
    }

    public Work getWork() {
        return work;
    }

    public Teacher getVerifier() {
        return verifier;
    }

    public LocalDate getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDate verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
