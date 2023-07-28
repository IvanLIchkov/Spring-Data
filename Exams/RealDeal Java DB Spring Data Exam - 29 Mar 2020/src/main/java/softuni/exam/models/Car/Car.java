package softuni.exam.models.Car;

import org.hibernate.validator.constraints.Length;
import softuni.exam.models.Offer;
import softuni.exam.models.Picture.Picture;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cars",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"make","model", "kilometers"})})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Length(min = 2, max = 20)
    private String make;

    @Column(nullable = false)
    @Length(min = 2, max = 20)
    private String model;

    @Column(nullable = false)
    @Positive
    private int kilometers;

    @Column(nullable = false, name = "regitered_on")
    private LocalDate registeredOn;


    public Car() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }
}
