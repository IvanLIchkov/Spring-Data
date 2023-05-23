package com.example.football.models.dto.ImportPlayer;

import com.example.football.util.DateAdapter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


public class ImportPlayerDto {

    @XmlElement(name = "first-name")
    @Size(min = 3)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 3)
    private String lastName;

    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate birthDate;

    @XmlElement
    private String position;

    @XmlElement(name = "town")
    private ImportTownNameDto town;

    @XmlElement(name = "team")
    private ImportTeamNameDto team;

    @XmlElement(name = "stat")
    private ImportStatIdDto stat;

    public ImportPlayerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPosition() {
        return position;
    }

    public ImportTownNameDto getTown() {
        return town;
    }

    public ImportTeamNameDto getTeam() {
        return team;
    }

    public ImportStatIdDto getStat() {
        return stat;
    }
}
