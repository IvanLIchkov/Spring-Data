package com.product_shop.entities.users.UsersAndProductsDto;

import com.product_shop.entities.products.Product;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private int age;

    @XmlElement
    private SoldProductCountDto soldProductCountDto;

    public ExportUserDto() {
    }

    public ExportUserDto(String firstName, String lastName, int age, long count, List<Product> exportSoldProductsDtos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProductCountDto = new SoldProductCountDto(count, exportSoldProductsDtos);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public SoldProductCountDto getSoldProductCountDto() {
        return soldProductCountDto;
    }
}
