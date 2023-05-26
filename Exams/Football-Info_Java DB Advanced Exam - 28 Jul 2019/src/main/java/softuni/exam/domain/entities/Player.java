package softuni.exam.domain.entities;

import javax.persistence.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "palyers")
public class Player {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @Length(min = 3, max = 15)
    @NotNull
    private String lastName;

    @Column
    @NotNull
    @Min(1)
    @Max(99)
    private int number;

    @Column
    @NotNull
    @Min(0)
    private BigDecimal salary;

    @Column
    @NotNull
    @Length(min = 2, max = 2)
    private String position;

    @ManyToOne
    @NotNull
    private Picture picture;

    @ManyToOne
    @NotNull
    private Team team;

    public Player() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
