package exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message) {

        super(message);
    }
}
