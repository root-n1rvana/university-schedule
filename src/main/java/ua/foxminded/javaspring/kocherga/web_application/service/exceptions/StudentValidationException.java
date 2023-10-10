package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class StudentValidationException extends RuntimeException {

    public StudentValidationException(String message) {
        super(message);
    }

    public StudentValidationException() {
    }

    public StudentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentValidationException(Throwable cause) {
        super(cause);
    }
}
