package com.product_shop.entities.products;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportProductsInRangeDto {

 @XmlElement(name = "product")
    private List<ExportProductInRangeDto> products;

    public ExportProductsInRangeDto() {
    }

    public ExportProductsInRangeDto(List<ExportProductInRangeDto> products) {
        this.products = products;
    }
}
