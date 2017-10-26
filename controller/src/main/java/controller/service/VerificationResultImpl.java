package controller.service;

import controller.dao.dto.VerificationResultDTO;
import controller.dao.VerificationDAO;
import controller.dao.daoimplementation.VerificationDAOImpl;

public class VerificationResultImpl implements VerificationService {
    VerificationDAO verificationDAO = new VerificationDAOImpl();

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        return verificationDAO.persistVerificationResult(result);
    }
}
