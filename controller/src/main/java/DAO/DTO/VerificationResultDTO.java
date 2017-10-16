package DAO.DTO;

public class VerificationResultDTO {
    public int workId;
    public int verifierId;
    public int mark;
    public String comment;

    public VerificationResultDTO(int workId, int verifierId, int mark, String comment) {
        this.workId = workId;
        this.verifierId = verifierId;
        this.mark = mark;
        this.comment = comment;
    }
}
