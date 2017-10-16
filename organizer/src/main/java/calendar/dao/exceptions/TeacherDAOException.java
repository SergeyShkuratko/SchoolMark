package calendar.dao.exceptions;

public class TeacherDAOException extends Exception{
    public TeacherDAOException() {
    }

    public TeacherDAOException(String message) {
        super(message);
    }

    public TeacherDAOException(Throwable cause) {
        super(cause);
    }

    public TeacherDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
