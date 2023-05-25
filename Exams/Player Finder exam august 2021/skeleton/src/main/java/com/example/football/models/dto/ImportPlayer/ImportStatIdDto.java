package com.example.football.models.dto.ImportPlayer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportStatIdDto {

    @XmlElement
    private Long id;

    public ImportStatIdDto() {
    }

    public Long getId() {
        return id;
    }
}
