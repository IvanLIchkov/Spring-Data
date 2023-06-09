package softuni.exam.domain.entities.dto.teamDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTeamsDto {

    @XmlElement(name = "team")
    private List<ImportTeamDto> teams;

    public ImportTeamsDto() {
    }

    public List<ImportTeamDto> getTeams() {
        return teams;
    }
}
