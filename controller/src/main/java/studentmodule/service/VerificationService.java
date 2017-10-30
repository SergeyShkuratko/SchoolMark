package studentmodule.service;

import studentmodule.dao.dto.VerificationResultDTO;

public interface VerificationService {

    boolean persistVerificationResult(VerificationResultDTO result);
}
