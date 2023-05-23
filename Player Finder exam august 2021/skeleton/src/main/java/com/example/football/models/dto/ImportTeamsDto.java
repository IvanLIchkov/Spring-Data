package com.example.football.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ImportTeamsDto {

    @Size(min = 3)
    private String name;

    @Size(min = 3)
    private String stadiumName;

    @Min(1000)
    private int fanBase;

    @Size(min = 10)
    private String history;
    private String townName;

    public ImportTeamsDto() {
    }


    public String getName() {
        return name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public int getFanBase() {
        return fanBase;
    }

    public String getHistory() {
        return history;
    }

    public String getTownName() {
        return townName;
    }


//    public boolean isValidTeam(){
//        if(this.name.length()<3){
//            return false;
//        }
//        if(this.stadiumName.length()<3){
//            return false;
//        }
//        if(this.fanBase<100){
//            return false;
//        }
//        return this.history.length()>=10;
//    }
}
