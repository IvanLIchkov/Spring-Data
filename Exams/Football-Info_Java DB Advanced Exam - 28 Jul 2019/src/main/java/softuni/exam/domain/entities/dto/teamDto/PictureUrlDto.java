package softuni.exam.domain.entities.dto.teamDto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

public class PictureUrlDto {

    @XmlElement
    @NotNull
    private String url;

    public PictureUrlDto() {
    }

    public String getUrl() {
        return url;
    }
}
