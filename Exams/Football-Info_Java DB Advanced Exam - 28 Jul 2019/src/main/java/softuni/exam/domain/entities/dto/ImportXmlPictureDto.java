package softuni.exam.domain.entities.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportXmlPictureDto {

    @XmlElement(name = "url")
    @NotNull
    private String url;

    public ImportXmlPictureDto() {
    }

    public String getUrl() {
        return url;
    }
}
