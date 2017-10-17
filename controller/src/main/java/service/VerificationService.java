package service;

import DAO.DTO.VerificationResultDTO;

public interface VerificationService {

    boolean persistVerificationResult(VerificationResultDTO result);
}
