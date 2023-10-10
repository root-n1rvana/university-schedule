package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class RegistrationValidationException extends RuntimeException {

    public RegistrationValidationException(String message) {
        super(message);
    }

    public RegistrationValidationException() {
    }

    public RegistrationValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationValidationException(Throwable cause) {
        super(cause);
    }
}
