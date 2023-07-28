package softuni.exam.models.Picture.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ImportPictureDto {

    @Length(min = 2, max = 20)
    @NotNull
    private String name;

    @NotNull
    private String dateAndTime;

    @NotNull
    private int car;

    public ImportPictureDto() {
    }

    public String getName() {
        return name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public int getCar() {
        return car;
    }
}
