package com.product_shop.entities.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDto> categories;

    public CategoryImportDTO() {
    }

    public List<CategoryNameDto> getCategories() {
        return categories;
    }

}
