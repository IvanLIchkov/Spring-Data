package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ImportConstellationDto {

    @NotNull
    @Length(min = 3, max = 20)
    private String name;

    @NotNull
    @Length(min = 5)
    private String description;

    public ImportConstellationDto() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
