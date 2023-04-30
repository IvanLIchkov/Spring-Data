package entities;

import javax.persistence.*;

@Entity
@Table
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinColumn
    private Country country;

    public Town(){}

    public Town(String name) {
        this();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountrie() {
        return country;
    }

    public void setCountrie(Country country) {
        this.country = country;
    }
}

