package student.service;

import student.dao.dto.VerificationResultDTO;
import student.dao.VerificationDAO;
import student.dao.daoimplementation.VerificationDAOImpl;

public class VerificationResultImpl implements VerificationService {
    VerificationDAO verificationDAO = new VerificationDAOImpl();

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        return verificationDAO.persistVerificationResult(result);
    }
}
