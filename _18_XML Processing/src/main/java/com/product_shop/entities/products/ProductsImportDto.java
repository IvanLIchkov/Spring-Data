package com.product_shop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportDto {

    @XmlElement(name = "product")
    private List<ProductImportDto> products;

    public ProductsImportDto() {
    }

    public List<ProductImportDto> getProducts() {
        return products;
    }
}
