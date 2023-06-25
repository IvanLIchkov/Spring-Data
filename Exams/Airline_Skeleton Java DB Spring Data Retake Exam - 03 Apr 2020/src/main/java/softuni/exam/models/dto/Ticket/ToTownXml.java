package softuni.exam.models.dto.Ticket;

import javax.xml.bind.annotation.XmlElement;

public class ToTownXml {

    @XmlElement
    private String name;

    public ToTownXml() {
    }

    public String getName() {
        return name;
    }
}
