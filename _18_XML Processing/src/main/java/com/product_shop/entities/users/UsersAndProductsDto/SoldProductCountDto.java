package com.product_shop.entities.users.UsersAndProductsDto;

import com.product_shop.entities.products.Product;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "sold-products")
public class SoldProductCountDto {

    @XmlElement(name = "count")
    private long count;


    List<ExportSoldProductsDto> products;

    public SoldProductCountDto() {
    }

    public SoldProductCountDto(long count, List<Product> products) {
        this.count = count;
        this.products = mapProducts(products);
    }

    private List<ExportSoldProductsDto> mapProducts(List<Product> products){
        return products.stream().map(p-> new ExportSoldProductsDto(p.getName(),p.getPrice())).collect(Collectors.toList());
    }

    public long getCount() {
        return count;
    }

    public List<ExportSoldProductsDto> getProducts() {
        return products;
    }
}
