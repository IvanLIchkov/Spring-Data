package softuni.exam.models.dto.importTasks;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mechanic")
public class MechanicFirstNameXml {

    @XmlElement
    @NotNull
    @Length(min = 2)
    private String firstName;

    public MechanicFirstNameXml() {
    }

    public String getFirstName() {
        return firstName;
    }
}
