package dao;

import dao.dto.VerificationResultDTO;

public interface VerificationDAO {
    boolean persistVerificationResult(VerificationResultDTO result);
}
