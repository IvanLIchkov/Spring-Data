package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "stars")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "light_years", nullable = false)
    @Positive
    private double lightYears;

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(name = "star_type", nullable = false)
    private String starType;

    @OneToMany(mappedBy = "observingStar")
    private List<Astronomer> observers;

    @ManyToOne
    private Constellation constellation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public List<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(List<Astronomer> observers) {
        this.observers = observers;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    @Override
    public String toString() {

       return String.format("Star: %s\n" +
                "   *Distance: %.2f light years\n" +
                "   **Description: %s\n" +
                "   ***Constellation: %s",this.name, this.lightYears, this.description, this.constellation.getName());
    }
}
