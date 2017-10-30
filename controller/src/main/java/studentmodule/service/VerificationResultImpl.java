package studentmodule.service;

import studentmodule.dao.dto.VerificationResultDTO;
import studentmodule.dao.VerificationDAO;
import studentmodule.dao.daoimplementation.VerificationDAOImpl;

public class VerificationResultImpl implements VerificationService {
    VerificationDAO verificationDAO = new VerificationDAOImpl();

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        return verificationDAO.persistVerificationResult(result);
    }
}
