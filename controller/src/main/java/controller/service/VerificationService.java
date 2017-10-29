package controller.service;

import controller.dao.dto.VerificationResultDTO;

public interface VerificationService {

    boolean persistVerificationResult(VerificationResultDTO result);
}
