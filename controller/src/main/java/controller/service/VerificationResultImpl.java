package controller.service;

import controller.dao.dto.VerificationResultDTO;
import controller.dao.VerificationDAO;
import controller.dao.daoimplementation.VerificationDAOImpl;
import org.springframework.stereotype.Service;

@Service
public class VerificationResultImpl implements VerificationService {
    VerificationDAO verificationDAO;

    public VerificationResultImpl(VerificationDAO verificationDAO) {
        this.verificationDAO = verificationDAO;
    }

    @Override
    public boolean persistVerificationResult(VerificationResultDTO result) {
        return verificationDAO.persistVerificationResult(result);
    }
}
