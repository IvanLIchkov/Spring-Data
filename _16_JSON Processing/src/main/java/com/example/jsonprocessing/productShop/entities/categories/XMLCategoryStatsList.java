package com.example.jsonprocessing.productShop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories-stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatsList {

    @XmlElementWrapper(name = "categories")
    private List<XmlCategoryStatsDto> stats;

    public XMLCategoryStatsList() {
    }

    public XMLCategoryStatsList(List<XmlCategoryStatsDto> stats) {
        this.stats = stats;
    }
}
