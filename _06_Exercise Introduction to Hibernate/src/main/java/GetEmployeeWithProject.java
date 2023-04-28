import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        int id = new Scanner(System.in).nextInt();

        entityManager.getTransaction().begin();
        System.out.println(entityManager.createQuery("FROM Employee e where e.id = ?1", Employee.class)
                .setParameter(1, id)
                .getSingleResult()
                .toString());
        entityManager.getTransaction().commit();
    }
}
