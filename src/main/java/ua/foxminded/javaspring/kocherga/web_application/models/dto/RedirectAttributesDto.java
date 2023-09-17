package ua.foxminded.javaspring.kocherga.web_application.models.dto;

public class RedirectAttributesDto {

    private String name;
    private String value;

    public RedirectAttributesDto() {
    }

    public RedirectAttributesDto(String name) {
        this.name = name;
    }

    public RedirectAttributesDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
