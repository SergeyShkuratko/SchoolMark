package DAO;

import DAO.DTO.VerificationResultDTO;

public interface VerificationDAO {
    boolean persistVerificationResult(VerificationResultDTO result);
}
