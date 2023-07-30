package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "astronomers")
public class Astronomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private double salary;

    @Column(name = "average_observation_hours ")
    @NotNull
    private double averageObservationHours;

    @Column(nullable = true)
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "observing_star_id")
    private Star observingStar;

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Star getObservingStar() {
        return observingStar;
    }

    public void setObservingStar(Star observingStar) {
        this.observingStar = observingStar;
    }
}
