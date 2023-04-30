package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @OneToMany(targetEntity = Team.class, mappedBy = "primaryKit", cascade = CascadeType.ALL)
    private List<Team> teamsPrimaryKit;

    @OneToMany(targetEntity = Team.class, mappedBy = "secondaryKit", cascade = CascadeType.ALL)
    private List<Team> teamsSecondaryKit;

    public Color(String name) {
        this();
        this.name = name;
    }

    public  Color(){
        this.teamsPrimaryKit = new ArrayList<>();
        this.teamsSecondaryKit = new ArrayList<>();
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

    public List<Team> getTeamsPrimaryKit() {
        return teamsPrimaryKit;
    }

    public void setTeamsPrimaryKit(List<Team> teamsPrimaryKit) {
        this.teamsPrimaryKit = teamsPrimaryKit;
    }

    public List<Team> getTeamsSecondaryKit() {
        return teamsSecondaryKit;
    }

    public void setTeamsSecondaryKit(List<Team> teamsSecondaryKit) {
        this.teamsSecondaryKit = teamsSecondaryKit;
    }
}
