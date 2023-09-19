package ua.foxminded.javaspring.kocherga.web_application.models;

public enum DefaultGroup {
    ADMIN(1L),
    PROFESSOR(2L),
    STUDENT(3L),
    UNSELECTED(9L);

    private final Long id;

    DefaultGroup(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
