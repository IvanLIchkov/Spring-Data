package softuni.exam.models.Car.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ImportCarsDto {

    @Length(min = 2, max = 20)
    @NotNull
    private String make;

    @Length(min = 2, max = 20)
    @NotNull
    private String model;

    @Positive
    @NotNull
    private int kilometers;

    @NotNull
    private String registeredOn;

    public ImportCarsDto() {
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getKilometres() {
        return kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }


}
