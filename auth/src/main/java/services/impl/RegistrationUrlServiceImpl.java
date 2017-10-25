package services.impl;

import classes.Role;
import dao.RegistrationDAOImpl;
import interfaces.dao.RegistrationUrlDAO;
import services.RegistrationUrlService;

import java.util.Random;

public class RegistrationUrlServiceImpl implements RegistrationUrlService {
    static private RegistrationUrlDAO registrationUrlDAO = new RegistrationDAOImpl();
    static private final int COUNT_CHAR_IN_URL = 13;
    @Override
    public String generateUrl(Role role, int schoolId, int classId) {
        String oldUrl = registrationUrlDAO.getUrl(role, schoolId, classId);
        String newUrl = generateUrl();
        while (registrationUrlDAO.getUrlDTO(newUrl)!=null) newUrl = generateUrl();
        registrationUrlDAO.setUrl(newUrl, oldUrl, role, schoolId, classId);
        return newUrl;
    }

    private String generateUrl(){
        StringBuilder sb = new StringBuilder("r");
        Random random = new Random();
        for(int i=1; i<COUNT_CHAR_IN_URL; i++){
            sb.append(random.nextInt());
        }
        return sb.toString();
    }
}
