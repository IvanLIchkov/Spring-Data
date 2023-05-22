package com.product_shop.entities.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportCategoriesDto {

    @XmlElement(name = "category")
    private List<ExportCategoryByCountDto> categories;

    public ExportCategoriesDto() {
    }

    public ExportCategoriesDto(List<ExportCategoryByCountDto> categories) {
        this.categories = categories;
    }

    public List<ExportCategoryByCountDto> getCategories() {
        return categories;
    }
}
