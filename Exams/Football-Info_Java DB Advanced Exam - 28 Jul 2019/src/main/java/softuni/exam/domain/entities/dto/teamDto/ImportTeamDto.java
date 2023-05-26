package softuni.exam.domain.entities.dto.teamDto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTeamDto {

    @XmlElement
    @Length(min = 2, max = 20)
    @NotNull
    private String name;

    @XmlElement(name = "picture")
    @NotNull
    private PictureUrlDto picture;

    public ImportTeamDto() {
    }

    public String getName() {
        return name;
    }

    public PictureUrlDto getPicture() {
        return picture;
    }
}
