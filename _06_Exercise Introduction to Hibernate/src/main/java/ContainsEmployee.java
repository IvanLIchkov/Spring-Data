import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final String isExistName = new Scanner(System.in).nextLine();

        entityManager.getTransaction().begin();

            final List<Employee> result = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        boolean isMatch = result.stream().anyMatch(employee -> (String.format("%s %s",employee.getFirstName(),employee.getLastName())).equals(isExistName));

        if(isMatch){
            System.out.println("Yes");
            return;
        }
        System.out.println("No");


        entityManager.getTransaction().commit();
    }
}
