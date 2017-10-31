package studentmodule.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import studentmodule.dao.DAOStudentWork;
import studentmodule.dto.DTOFile;
import studentmodule.dto.DTOVariant;
import studentmodule.dto.DTOWork;
import studentmodule.exception.DAOStudentWorkException;

import java.util.Collections;
import java.util.List;

@Service
public class WorkService {

    private static final Logger logger = Logger.getLogger(WorkService.class);
    private final DAOStudentWork workDAO;

    public WorkService(DAOStudentWork workDAO) {
        this.workDAO = workDAO;
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

    public int addStudentFileToBD(int work_id, String file) throws DAOStudentWorkException {
        return workDAO.addStudentFile(work_id, file);
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

    public int setWorkStatus(int work_id, String status) throws DAOStudentWorkException {
        return workDAO.setWorkStatus(work_id, status);
    }

    public DTOFile getStudentFileUrlById(int fileid)
            throws DAOStudentWorkException {
        return workDAO.getStudentFileUrlById(fileid);
    }

    public DTOFile getStudentFileUrlByIdAndDeleteRecord(int fileid)
            throws DAOStudentWorkException {
        DTOFile dtoFile = workDAO.getStudentFileUrlById(fileid);
        if(dtoFile != null) {
            workDAO.delStudentFile(dtoFile.getId());
        }
        return dtoFile;
    }

    public List<DTOVariant> getVariants(int templ_id) {

        try {
            return workDAO.getVariants(templ_id);
        } catch (DAOStudentWorkException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    public void setWorkVariant(int workId, int variantId) {
        try {
            workDAO.setWorkVariant(workId, variantId);
        } catch (DAOStudentWorkException e) {
            logger.error(e.getMessage());
        }
    }

}
