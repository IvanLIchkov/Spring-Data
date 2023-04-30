package entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_date", nullable = false)
    private String visitDate;

    @Column(nullable = false)
    private String comments;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Patient patient;

    public Visitation(){}

    public Visitation(String visitDate, String comments, Patient patient) {
        this();
        this.visitDate = visitDate;
        this.comments = comments;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
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
