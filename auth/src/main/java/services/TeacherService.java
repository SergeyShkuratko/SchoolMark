package services;

import exceptions.SchoolTeacherDAOException;

import java.util.List;

public interface TeacherService {
    List<String> getTeacherNamesBySchoolId(int schoolId) throws SchoolTeacherDAOException;
}
