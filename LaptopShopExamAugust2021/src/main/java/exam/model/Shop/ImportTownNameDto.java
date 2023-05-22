package exam.model.Shop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImportTownNameDto {

    @XmlElement
    private String name;

    public ImportTownNameDto() {
    }

    public String getName() {
        return name;
    }
}
