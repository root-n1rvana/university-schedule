package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class UserValidationException extends RuntimeException {

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException() {
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserValidationException(Throwable cause) {
        super(cause);
    }
}
