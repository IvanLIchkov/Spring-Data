package softuni.exam.models.dto.Ticket;

import org.hibernate.validator.constraints.Length;
import softuni.exam.util.LocalDateAdapterXml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ImportTicketDto {

    @XmlElement(name = "serial-number")
    @NotNull
    @Length(min = 2)
    private String serialNumber;

    @XmlElement(name = "price")
    @NotNull
    @Positive
    private BigDecimal price;

    @XmlElement(name = "take-off")
    @NotNull
    @XmlJavaTypeAdapter(value = LocalDateAdapterXml.class)
    private LocalDateTime takeOff;

    @XmlElement(name = "from-town")
    @NotNull
    private FromTownXml fromTown;

    @XmlElement(name = "to-town")
    @NotNull
    private ToTownXml toTown;

    @XmlElement
    @NotNull
    private PassengerXml passenger;

    @XmlElement
    @NotNull
    private PlaneXml plane;

    public ImportTicketDto() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public FromTownXml getFromTown() {
        return fromTown;
    }

    public ToTownXml getToTown() {
        return toTown;
    }

    public PassengerXml getPassenger() {
        return passenger;
    }

    public PlaneXml getPlane() {
        return plane;
    }
}
