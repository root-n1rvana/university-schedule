package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class TeacherValidationException extends RuntimeException {

    public TeacherValidationException(String message) {
        super(message);
    }

    public TeacherValidationException() {
    }

    public TeacherValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherValidationException(Throwable cause) {
        super(cause);
    }
}
