package studentmodule.dao;

import studentmodule.dao.dto.VerificationResultDTO;

public interface VerificationDAO {
    boolean persistVerificationResult(VerificationResultDTO result);
}
