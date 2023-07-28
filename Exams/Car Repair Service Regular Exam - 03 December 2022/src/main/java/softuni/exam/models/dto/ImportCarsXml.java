package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCarsXml {

    @XmlElement(name = "car")
    List<ImportCarXml> cars;

    public ImportCarsXml() {
    }

    public List<ImportCarXml> getCars() {
        return cars;
    }
}
