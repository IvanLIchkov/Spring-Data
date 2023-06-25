package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ImportTownDto {

    @Length(min = 2)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private int population;

    @NotNull
    private String guide;

    public ImportTownDto() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getGuide() {
        return guide;
    }
}
