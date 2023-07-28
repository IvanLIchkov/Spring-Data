package softuni.exam.models.dto.importTasks;

import softuni.exam.util.DateAdapterXml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTaskXml {

    @XmlElement(name = "date")
    @NotNull
    @XmlJavaTypeAdapter(DateAdapterXml.class)
    private LocalDateTime date;

    @XmlElement(name = "price")
    @NotNull
    @Positive
    private double price;

    @XmlElement(name = "car")
    @NotNull
    private CarIdXml car;

    @XmlElement(name = "mechanic")
    @NotNull
    private MechanicFirstNameXml mechanic;

    @XmlElement(name = "part")
    @NotNull
    private PartIdXml part;

    public ImportTaskXml() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public CarIdXml getCar() {
        return car;
    }

    public MechanicFirstNameXml getMechanic() {
        return mechanic;
    }

    public PartIdXml getPart() {
        return part;
    }
}
