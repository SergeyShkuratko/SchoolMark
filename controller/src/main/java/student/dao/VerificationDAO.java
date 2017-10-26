package student.dao;

import student.dao.dto.VerificationResultDTO;

public interface VerificationDAO {
    boolean persistVerificationResult(VerificationResultDTO result);
}
