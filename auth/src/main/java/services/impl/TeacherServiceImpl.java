package services.impl;

import classes.dto.TeacherDTO;
import exceptions.SchoolTeacherDAOException;
import interfaces.dao.SchoolTeacherDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import services.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    private static Logger logger = Logger.getLogger(TeacherServiceImpl.class);

    private SchoolTeacherDAO teacherDAO;

    public TeacherServiceImpl(SchoolTeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<String> getTeacherNamesBySchoolId(int schoolId) throws SchoolTeacherDAOException {
        List<String> names = new ArrayList<>();
        try {
            List<TeacherDTO> classes = teacherDAO.getTeacherBySchool(schoolId);
            names = classes.stream().map((t) -> getTeacherFullName(t)).collect(Collectors.toList());
        } catch (SchoolTeacherDAOException e) {
            logger.info(e);
            throw e;
        }
        return names;
    }

    private String getTeacherFullName(TeacherDTO teacherDTO) {
        String fullName = teacherDTO.lastname;
        if (teacherDTO.firstname != null) {
            fullName += " " + teacherDTO.firstname.substring(0, 1) + ".";
        }
        if (teacherDTO.patronymic != null) {
            fullName += " " + teacherDTO.patronymic.substring(0, 1) + ".";
        }
        return fullName;
    }
}
