package controller.dao;

import controller.dao.dto.VerificationResultDTO;

public interface VerificationDAO {
    boolean persistVerificationResult(VerificationResultDTO result);
}
