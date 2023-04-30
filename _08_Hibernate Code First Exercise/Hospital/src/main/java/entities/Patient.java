package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column
    private String email;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "medical_insurance", nullable = false)
    private boolean medicalInsurance;

    @OneToMany
    @JoinTable
    private List<Visitation> visitations;

    @OneToMany
    @JoinTable
    private List<Diagnose> diagnoses;

    @ManyToMany
    @JoinTable
    private List<Medicament> medicaments;

    public Patient(){
        this.visitations = new ArrayList<>();
        this.diagnoses = new ArrayList<>();
    }

    public Patient(String firstName, String lastName, String address, String email, String dateOfBirth, boolean medicalInsurance) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.medicalInsurance = medicalInsurance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(boolean medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public List<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(List<Visitation> visitations) {
        this.visitations = visitations;
    }

    public List<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public void addVisitation(Visitation visitation){
        this.visitations.add(visitation);
    }
    public void addDiagnose(Diagnose diagnose){
        this.diagnoses.add(diagnose);
    }
    public void addMedicament(Medicament medicament){
        this.medicaments.add(medicament);
    }
}
