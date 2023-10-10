package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class LessonValidationException extends RuntimeException {

    public LessonValidationException(String message) {
        super(message);
    }

    public LessonValidationException() {
    }

    public LessonValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LessonValidationException(Throwable cause) {
        super(cause);
    }
}
