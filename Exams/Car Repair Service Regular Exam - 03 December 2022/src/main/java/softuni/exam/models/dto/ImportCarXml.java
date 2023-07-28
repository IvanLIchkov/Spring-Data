package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;
import softuni.exam.models.entity.CarType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCarXml {

    @XmlElement(name = "carMake")
    @Length(min = 2, max = 30)
    @NotNull
    private String carMake;

    @XmlElement
    @Length(min = 2, max = 30)
    @NotNull
    private String carModel;

    @XmlElement
    @Positive
    @NotNull
    private String year;

    @XmlElement
    @Length(min = 2, max = 30)
    @NotNull
    private String plateNumber;

    @XmlElement
    @Positive
    @NotNull
    private String kilometers;

    @XmlElement
    @Min(1)
    private double engine;

    @XmlElement
    private CarType carType;

    public ImportCarXml() {
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getYear() {
        return year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getKilometers() {
        return kilometers;
    }

    public double getEngine() {
        return engine;
    }

    public CarType getCarType() {
        return carType;
    }
}
