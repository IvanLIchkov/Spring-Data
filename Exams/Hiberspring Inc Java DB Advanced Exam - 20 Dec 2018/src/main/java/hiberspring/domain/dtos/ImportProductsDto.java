package hiberspring.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductsDto {


    @XmlElement(name = "product")
    List<ImportProductDto> products;

    public ImportProductsDto() {
    }

    public List<ImportProductDto> getProducts() {
        return products;
    }
}
