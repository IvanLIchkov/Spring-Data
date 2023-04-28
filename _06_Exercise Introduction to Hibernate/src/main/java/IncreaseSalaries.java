import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.getTransaction().begin();
        List<String>departments = new ArrayList<>();
        departments.add("Tool Design");
        departments.add("Engineering");
        departments.add("Marketing or Information Services");
        entityManager.createQuery("from Employee where department.name in ?1 ",Employee.class)
                .setParameter(1,departments)
                .getResultList()
                .forEach(e-> {
                     entityManager.createQuery("UPDATE Employee emp set salary = salary*1.12 where emp.id =?1")
                            .setParameter(1,e.getId())
                             .executeUpdate();
                    System.out.printf("%s %s ($%.2f)%n",e.getFirstName(), e.getLastName(), e.getSalary());
                });
        entityManager.getTransaction().commit();
    }
}
