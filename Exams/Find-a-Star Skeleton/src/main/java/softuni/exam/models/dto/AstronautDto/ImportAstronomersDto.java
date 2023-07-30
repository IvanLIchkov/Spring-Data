package softuni.exam.models.dto.AstronautDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportAstronomersDto {

    @XmlElement(name = "astronomer")
    List<ImportAstronomerDto> astronomerDtos;

    public ImportAstronomersDto() {
    }

    public List<ImportAstronomerDto> getAstronomerDtos() {
        return astronomerDtos;
    }
}
