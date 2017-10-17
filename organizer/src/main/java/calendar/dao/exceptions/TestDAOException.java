package calendar.dao.exceptions;

public class TestDaoException extends Exception {
    public TestDaoException() {
    }

    public TestDaoException(String message) {
        super(message);
    }

    public TestDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestDaoException(Throwable cause) {
        super(cause);
    }
}
