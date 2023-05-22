package com.product_shop.entities.users.UsersAndProductsDto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
public class ExportSoldProductsDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "price")
    private BigDecimal price;

    public ExportSoldProductsDto() {
    }

    public ExportSoldProductsDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
