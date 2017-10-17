package service;

import DAO.DTO.VerificationResultDTO;
import DAO.VerificationDAO;
import DAO.daoimplementation.VerificationDAOImpl;

public class VerificationResultImpl implements VerificationService {
    VerificationDAO verificationDAO = new VerificationDAOImpl();

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        return verificationDAO.persistVerificationResult(result);
    }
}
