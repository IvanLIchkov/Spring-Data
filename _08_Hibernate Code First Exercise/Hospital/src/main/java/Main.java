import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    static final private EntityManager entityManager = Persistence.createEntityManagerFactory("hospital").createEntityManager();

    public static void main(String[] args) {
        boolean end = true;

        while (end){
            System.out.println("If you want to add patient press 1.");
            System.out.println("If you want to add diagnose press 2.");
            System.out.println("If you want to add medicament press 3.");
            System.out.println("If you want to add visitations press 4.");
            System.out.println("If you want to close press 5.");
            int number = Integer.parseInt(sc.nextLine());



            entityManager.getTransaction().begin();
            switch (number){
                case 1:
                    entityManager.persist(addPatient());
                    break;
                case 2:
                    entityManager.persist(addDiagnose());
                    break;
                case 3:
                    entityManager.persist(addMedicament());
                    break;
                case 4:
                    entityManager.persist(addVisitation());
                    break;
                case 5:
                    end = false;
                    break;
            }
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
    public static Patient getPatient(){
        System.out.println("Patient first name:");
        String firstName = sc.nextLine();
        System.out.println("Patient last name:");
        String lastName = sc.nextLine();
        return entityManager.createQuery("FROM Patient p where p.firstName like ?1 and p.lastName like ?2", Patient.class)
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .getSingleResult();
    }
    public static Visitation addVisitation(){
        System.out.println("Date of visitation:");
        String date = sc.nextLine();
        System.out.println("Comments:");
        String comments = sc.nextLine();
        Patient patient = getPatient();

        Visitation visitation = new Visitation(date,comments, patient);
        patient.addVisitation(visitation);
        return visitation;
    }
    public static Medicament addMedicament(){
        System.out.println("Medicament name:");
        String name = sc.nextLine();
        Patient patient = getPatient();
        Medicament medicament = new Medicament(name, patient);
        patient.addMedicament(medicament);
        return medicament;
    }
    public static Diagnose addDiagnose(){
        System.out.println("Diagnose name:");
        String diagnoseName = sc.nextLine();
        System.out.println("Comments:");
        String comments = sc.nextLine();

        Patient patientToAddDiagnose = getPatient();
        Diagnose diagnose = new Diagnose(diagnoseName,comments,patientToAddDiagnose);
        patientToAddDiagnose.addDiagnose(diagnose);
        return diagnose;
    }

    public static Patient addPatient (){
        System.out.println("Insert first name:");
        String firstName = sc.nextLine();
        System.out.println("Insert last name:");
        String lastName = sc.nextLine();
        System.out.println("Insert address:");
        String address = sc.nextLine();
        System.out.println("Insert email:");
        String email = sc.nextLine();
        System.out.println("Insert date");
        String date = sc.nextLine();
        System.out.println("Did patient have insurance? yes/no");
        String insurance = sc.nextLine();

        boolean isInsurance = false;


        if(insurance.equals("yes")){
            isInsurance = true;
        }
        return new Patient(firstName,lastName,address,email,date,isInsurance);
    }
}