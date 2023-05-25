package com.example.football.models.dto.ImportPlayer;

import javax.xml.bind.annotation.XmlElement;

public class ImportTeamNameDto {

    @XmlElement
    private String name;

    public ImportTeamNameDto() {
    }

    public String getName() {
        return name;
    }
}
