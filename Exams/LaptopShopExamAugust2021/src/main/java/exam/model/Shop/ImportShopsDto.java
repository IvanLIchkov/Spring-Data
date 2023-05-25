package exam.model.Shop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopsDto {

    @XmlElement(name = "shop")
    private List<ImportShopDto> shops;

    public ImportShopsDto() {
    }

    public List<ImportShopDto> getShops() {
        return shops;
    }

}
