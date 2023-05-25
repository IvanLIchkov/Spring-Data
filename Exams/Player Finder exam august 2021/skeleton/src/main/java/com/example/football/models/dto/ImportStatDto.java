package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlElement;

public class ImportStatDto {
    @XmlElement(name = "passing")
    private double passing;

    @XmlElement(name = "shooting")
    private double shooting;

    @XmlElement(name = "endurance")
    private double endurance;

    public ImportStatDto() {
    }

    public double getPassing() {
        return passing;
    }

    public double getShooting() {
        return shooting;
    }

    public double getEndurance() {
        return endurance;
    }

    public boolean isValidStat(){
        if(this.passing<=0){
            return false;
        }
        if(this.shooting<=0){
            return false;
        }
        return this.endurance>0;
    }
}
