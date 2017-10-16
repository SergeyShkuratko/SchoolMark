package exceptions;

public class UserDAOException extends Exception {
    public UserDAOException() {
    }

    public UserDAOException(String message) {

        super(message);
    }
}
