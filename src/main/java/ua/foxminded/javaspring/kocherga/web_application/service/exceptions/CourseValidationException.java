package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class CourseValidationException extends RuntimeException {

    public CourseValidationException(String message) {
        super(message);
    }

    public CourseValidationException() {
    }

    public CourseValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseValidationException(Throwable cause) {
        super(cause);
    }
}
