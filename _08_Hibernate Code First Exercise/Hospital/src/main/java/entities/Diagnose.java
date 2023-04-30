package entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String comments;

    @ManyToOne( cascade = CascadeType.ALL)
    @NotNull
    private Patient patient;

    public Diagnose(){}

    public Diagnose( String name, String comments, Patient patient) {
        this();
        this.name = name;
        this.comments = comments;
        this.patient = patient;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
