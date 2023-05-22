package com.product_shop.entities.users;





import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportDto {

    @XmlElement(name = "user")
    private List<UserImportDto> users;

    public UsersImportDto() {
    }

    public List<UserImportDto> getUsers() {
        return users;
    }
}
