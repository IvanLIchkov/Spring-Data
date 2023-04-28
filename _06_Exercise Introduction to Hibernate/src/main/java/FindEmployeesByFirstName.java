import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        String startString = new Scanner(System.in).nextLine();
        entityManager.getTransaction().begin();
            entityManager.createQuery("FROM Employee where firstName like concat(?1,'%') ", Employee.class)
                            .setParameter(1,startString)
                                    .getResultList()
                                            .forEach(e-> System.out.printf("%s %s - %s ($%.2f)%n",e.getFirstName(),e.getLastName(),e.getJobTitle(),e.getSalary()));
        entityManager.getTransaction().commit();
    }
}
