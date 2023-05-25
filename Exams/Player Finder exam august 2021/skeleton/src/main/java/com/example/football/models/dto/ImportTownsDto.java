package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportTownsDto {

    @Size(min = 2)
    private String name;

    @Positive
    private int population;

    @Size(min = 10)
    private String travelGuide;

    public ImportTownsDto() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

//    public boolean isValid(){
//        if(this.name.length()<2){
//            return false;
//        }
//        if(this.population<0){
//            return false;
//        }
//        return this.travelGuide.length() >= 10;
//    }
}
