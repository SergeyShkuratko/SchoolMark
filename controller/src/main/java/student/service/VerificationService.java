package student.service;

import student.dao.dto.VerificationResultDTO;

public interface VerificationService {

    boolean persistVerificationResult(VerificationResultDTO result);
}
