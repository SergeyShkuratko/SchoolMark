package service;

import dao.dto.VerificationResultDTO;
import dao.VerificationDAO;
import dao.daoimplementation.VerificationDAOImpl;

public class VerificationResultImpl implements VerificationService {
    VerificationDAO verificationDAO = new VerificationDAOImpl();

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        return verificationDAO.persistVerificationResult(result);
    }
}
