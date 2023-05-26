package softuni.exam.domain.entities;

import javax.persistence.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @Length(min = 2, max = 20)
    @NotNull
    private String name;

    @ManyToOne
    @NotNull
    private Picture picture;

    public Team() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    //TODO
}
