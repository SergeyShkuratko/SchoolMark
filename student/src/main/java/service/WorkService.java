package service;


import dao.DAOStudentWork;
import dto.DTOFile;
import dto.DTOWork;
import org.apache.log4j.Logger;

import java.util.List;

public class WorkService {

    private static final Logger logger = Logger.getLogger(WorkService.class);
    private static DAOStudentWork workDAO;

    static {
        try {
            workDAO = new DAOStudentWork();
        } catch (DAOStudentWork.DAOStudentWorkException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Возвращает список контрольных работ
     *
     * @param student_id ID студента
     * @return Список работ студента
     */
    public static List<DTOWork> getAllWork(int student_id) throws DAOStudentWork.DAOStudentWorkException {
        List<DTOWork> works = workDAO.getWorksByStudentId(student_id);
        return works;

    }

    /**
     * Возвращает контрольную работу по ID
     *
     * @param id
     * @return
     */
    public static DTOWork getWorkById(int id) throws DAOStudentWork.DAOStudentWorkException {
        return workDAO.getWorkById(id);
    }

    /**
     * Возвращает список файлов ученика прикрепленных к работе ученика
     *
     * @param id
     * @return
     */
    public static List<DTOFile> getStudentFilesByWorkId(int id) throws DAOStudentWork.DAOStudentWorkException {
        return workDAO.getStudentFilesByWorkId(id);
    }

    public static void addStudentFileToBD(int work_id, String file) throws DAOStudentWork.DAOStudentWorkException {
        workDAO.addStudentFile(work_id, file);
    }

    public static boolean delStudentFile(int file_id) throws DAOStudentWork.DAOStudentWorkException {
        return workDAO.delStudentFile(file_id);
    }


    /**
     * Возвращает файлы учителя по id контрольной работы
     *
     * @param verificationId
     * @return
     */
    public static List<DTOFile> getVerificationFilesByVerificationId(int verificationId) throws DAOStudentWork.DAOStudentWorkException {

        return workDAO.getVerificationFilesByVerificationId(verificationId);
    }

    /**
     * Возвращает список вопросов по Id контрольной работы ученика
     *
     * @param template_id
     * @return
     */
    public static List<String> getQuestionListByTemplateId(int template_id) throws DAOStudentWork.DAOStudentWorkException {
        return workDAO.getQuestionListByTemplateId(template_id);

    }

    public static int setWorkStatus(int work_id, String status) throws DAOStudentWork.DAOStudentWorkException {
        return workDAO.setWorkStatus(work_id, status);
    }

}
