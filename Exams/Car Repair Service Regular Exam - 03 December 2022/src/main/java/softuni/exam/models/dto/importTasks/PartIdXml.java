package softuni.exam.models.dto.importTasks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "part")
public class PartIdXml {

    @XmlElement
    private int id;

    public PartIdXml() {
    }

    public int getId() {
        return id;
    }
}
