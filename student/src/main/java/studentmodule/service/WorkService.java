package student.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import student.dao.DAOStudentWork;
import student.dto.DTOFile;
import student.dto.DTOWork;
import student.exception.DAOStudentWorkException;

import java.util.List;

@Service
public class WorkService {

    private static final Logger logger = Logger.getLogger(WorkService.class);
    private static DAOStudentWork workDAO;

    static {
        workDAO = new DAOStudentWork();
    }

    /**
     * Возвращает список контрольных работ
     *
     * @param user_id ID студента
     * @return Список работ студента
     */
    public List<DTOWork> getAllWork(int user_id) throws DAOStudentWorkException {
        try {
            List<DTOWork> works = workDAO.getWorksByStudentId(user_id);
            return works;
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return null;

    }

    /**
     * Возвращает контрольную работу по ID
     *
     * @param id
     * @return
     */
    public DTOWork getWorkById(int id) throws DAOStudentWorkException {
        try {
            return workDAO.getWorkById(id);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Возвращает список файлов ученика прикрепленных к работе ученика
     *
     * @param id
     * @return
     */
    public List<DTOFile> getStudentFilesByWorkId(int id) throws DAOStudentWorkException {
        try {
            return workDAO.getStudentFilesByWorkId(id);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void addStudentFileToBD(int work_id, String file) throws DAOStudentWorkException {
        workDAO.addStudentFile(work_id, file);
    }

    public boolean delStudentFile(int file_id) throws DAOStudentWorkException {
        return workDAO.delStudentFile(file_id);
    }


    /**
     * Возвращает файлы учителя по id контрольной работы
     *
     * @param verificationId
     * @return
     */
    public List<DTOFile> getVerificationFilesByVerificationId(int verificationId) throws DAOStudentWorkException {
        try {
            return workDAO.getVerificationFilesByVerificationId(verificationId);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Возвращает список вопросов по Id контрольной работы ученика
     *
     * @param variant_id
     * @return
     */
    public List<String> getQuestionListByVariantId(int variant_id) throws DAOStudentWorkException {
        try {
            return workDAO.getQuestionListByVariantId(variant_id);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return null;

    }

    public static int setWorkStatus(int work_id, String status) throws DAOStudentWorkException {
        return workDAO.setWorkStatus(work_id, status);
    }

}
