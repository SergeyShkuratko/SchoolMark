package calendar.dao.exceptions;

public class TestDAOException extends Exception {
    public TestDAOException() {
    }

    public TestDAOException(String message) {
        super(message);
    }

    public TestDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestDAOException(Throwable cause) {
        super(cause);
    }
}
