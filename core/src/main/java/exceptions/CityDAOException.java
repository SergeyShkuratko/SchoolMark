package exceptions;

public class CityDAOException extends Exception {

    public CityDAOException() {
        super();
    }

    public CityDAOException(Throwable cause) {
        super(cause);
    }

    public CityDAOException(String message) {
        super(message);
    }
}
