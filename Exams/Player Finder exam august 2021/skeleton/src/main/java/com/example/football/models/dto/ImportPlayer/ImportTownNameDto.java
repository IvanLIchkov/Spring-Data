package com.example.football.models.dto.ImportPlayer;


import javax.xml.bind.annotation.XmlElement;

public class ImportTownNameDto {

    @XmlElement(name = "name")
    private String name;

    public ImportTownNameDto() {
    }

    public String getName() {
        return name;
    }
}
