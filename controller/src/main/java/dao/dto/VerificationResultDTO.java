package dao.dto;

public class VerificationResultDTO {
    private int workId;
    private int verifierId;
    private int mark;
    private String comment;

    public VerificationResultDTO(int workId, int verifierId, int mark, String comment) {
        this.workId = workId;
        this.verifierId = verifierId;
        this.mark = mark;
        this.comment = comment;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(int verifierId) {
        this.verifierId = verifierId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
