package softuni.exam.models.dto.importTasks;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "car")
public class CarIdXml {

    @XmlElement
    @Positive
    private long id;

    public CarIdXml() {
    }

    public long getId() {
        return id;
    }
}
