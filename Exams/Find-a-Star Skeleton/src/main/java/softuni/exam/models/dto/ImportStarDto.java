package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;
import softuni.exam.models.entity.StarType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ImportStarDto {

    @NotNull
    @Length(min = 6)
    private String description;

    @Positive
    @NotNull
    private double lightYears;

    @Length(min = 2, max = 30)
    @NotNull
    private String name;

    @NotNull
    private String starType;

    @NotNull
    private long constellation;

    public ImportStarDto() {
    }

    public String getDescription() {
        return description;
    }

    public double getLightYears() {
        return lightYears;
    }

    public String getName() {
        return name;
    }

    public String getStarType() {
        return starType;
    }

    public long getConstellation() {
        return constellation;
    }
}
