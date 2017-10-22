package service;

import dao.dto.VerificationResultDTO;

public interface VerificationService {

    boolean persistVerificationResult(VerificationResultDTO result);
}
