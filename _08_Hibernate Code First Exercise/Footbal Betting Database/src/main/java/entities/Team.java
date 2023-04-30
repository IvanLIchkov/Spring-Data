package entities;

import javax.persistence.*;

@Entity
@Table
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column
    private String  logo;

    @Column(length = 3, nullable = false)
    private String initials;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Color primaryKit;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Color secondaryKit;


    @ManyToOne(cascade = CascadeType.ALL)
    private Town town;

    @Column
    private double budget;

    public Team(String name, String logo, String initials, Color primaryKit, Color secondaryKit, Town town, double budget) {
        this();
        this.name = name;
        this.logo = logo;
        this.initials = initials;
        this.primaryKit = primaryKit;
        this.secondaryKit = secondaryKit;
        this.town = town;
        this.budget = budget;
    }

    public Team(){}


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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKit() {
        return primaryKit;
    }

    public void setPrimaryKit(Color primaryKit) {
        this.primaryKit = primaryKit;
    }

    public Color getSecondaryKit() {
        return secondaryKit;
    }

    public void setSecondaryKit(Color secondaryKit) {
        this.secondaryKit = secondaryKit;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}

