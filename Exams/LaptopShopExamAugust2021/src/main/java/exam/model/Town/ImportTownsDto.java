package exam.model.Town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTownsDto {

    @XmlElement(name = "town")
    private List<ImportTownDto> towns;

    public ImportTownsDto() {
    }

    public ImportTownsDto(List<ImportTownDto> towns) {
        this.towns = towns;
    }


    public List<ImportTownDto> getTowns() {
        return towns;
    }

}
