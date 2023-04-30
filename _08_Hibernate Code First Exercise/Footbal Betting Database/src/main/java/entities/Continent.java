package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;

    public Continent(String name) {
        this();
        this.name = name;
    }

    public Continent (){
        this.countries = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCountry(Country country){
        this.countries.add(country);
    }
}

