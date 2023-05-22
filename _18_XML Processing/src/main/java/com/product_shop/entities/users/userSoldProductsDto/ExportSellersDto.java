package com.product_shop.entities.users.userSoldProductsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportSellersDto {

    @XmlElement(name = "user")
    private List<ExportUserWithSoldProductsDto> users;

    public ExportSellersDto() {
    }

    public ExportSellersDto(List<ExportUserWithSoldProductsDto> users) {
        this.users = users;
    }

    public void setUsers(List<ExportUserWithSoldProductsDto> users) {
        this.users = users;
    }
}
