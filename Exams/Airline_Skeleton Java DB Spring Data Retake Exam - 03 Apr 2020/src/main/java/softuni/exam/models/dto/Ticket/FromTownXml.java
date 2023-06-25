package softuni.exam.models.dto.Ticket;

import javax.xml.bind.annotation.XmlElement;

public class FromTownXml {

    @XmlElement
    private String name;

    public FromTownXml() {
    }

    public String getName() {
        return name;
    }
}
