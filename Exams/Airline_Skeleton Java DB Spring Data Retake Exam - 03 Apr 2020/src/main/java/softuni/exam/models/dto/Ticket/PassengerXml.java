package softuni.exam.models.dto.Ticket;

import javax.xml.bind.annotation.XmlElement;

public class PassengerXml {

    @XmlElement
    private String email;

    public PassengerXml() {
    }

    public String getEmail() {
        return email;
    }
}
