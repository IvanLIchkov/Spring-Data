package softuni.exam.models.dto.plane;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.*;

public class ImportPlaneDto {

    @XmlElement(name = "register-number")
    @Length(min = 5)
    @NotNull
    private String registerNumber;

    @XmlElement(name = "capacity")
    @Positive
    @NotNull
    private int capacity;

    @XmlElement(name = "airline")
    @Length(min = 2)
    @NotNull
    private String airline;

    public ImportPlaneDto() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAirline() {
        return airline;
    }
}
