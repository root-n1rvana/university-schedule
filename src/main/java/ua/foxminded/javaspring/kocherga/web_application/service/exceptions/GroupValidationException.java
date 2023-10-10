package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class GroupValidationException extends RuntimeException {

    public GroupValidationException(String message) {
        super(message);
    }

    public GroupValidationException() {
    }

    public GroupValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupValidationException(Throwable cause) {
        super(cause);
    }
}
