package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(length = 3)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
        joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id")
    )
    private Set<Continent> continents;


    public Country(String id, String name) {
        this();
        this.id = id;
        this.name = name;
    }



    public Country() {
        this.continents = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
