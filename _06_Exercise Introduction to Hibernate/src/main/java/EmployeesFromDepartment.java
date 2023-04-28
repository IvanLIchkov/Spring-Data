import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> resultList = entityManager.createQuery("FROM Employee e where e.department.name LIKE 'Research and Development' order by e.salary, e.id",Employee.class).getResultList();
        resultList.forEach(e-> System.out.printf("%s %s from Research and Development - $%s%n",e.getFirstName(),e.getLastName(),e.getSalary()));
        entityManager.getTransaction().commit();
    }
}
