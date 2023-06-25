package softuni.exam.models.dto.Ticket;

import javax.xml.bind.annotation.XmlElement;

public class PlaneXml {

    @XmlElement(name = "register-number")
    private String registerNumber;

    public PlaneXml() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }
}
