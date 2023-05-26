package softuni.exam.domain.entities.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportXmlPicturesDto {

    @XmlElement(name = "picture")
    private List<ImportXmlPictureDto> pictures;

    public ImportXmlPicturesDto() {
    }

    public List<ImportXmlPictureDto> getPictures() {
        return pictures;
    }
}
