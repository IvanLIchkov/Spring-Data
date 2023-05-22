package com.product_shop.entities.users.UsersAndProductsDto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDto {

    @XmlAttribute(name = "count")
    private Long count;

    @XmlElement(name = "user")
    private List<ExportUserDto> users;

    public UsersDto() {
    }

    public UsersDto(Long count, List<ExportUserDto> users) {
        this.count = count;
        this.users = users;
    }

    public Long getCount() {
        return count;
    }

    public List<ExportUserDto> getUsers() {
        return users;
    }
}
