package ua.foxminded.javaspring.kocherga.web_application.service.exceptions;

public class ScheduleValidationException extends RuntimeException {

    public ScheduleValidationException(String message) {
        super(message);
    }

    public ScheduleValidationException() {
    }

    public ScheduleValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScheduleValidationException(Throwable cause) {
        super(cause);
    }
}
