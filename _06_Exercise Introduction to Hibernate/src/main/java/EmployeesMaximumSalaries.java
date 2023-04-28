import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.getTransaction().begin();
            entityManager.createQuery("FROm Department ", Department.class)
                    .getResultList()
                            .forEach(d->{
                                BigDecimal bigDecimal = d.getEmployees().stream().map(Employee::getSalary).max(BigDecimal::compareTo).get();
                                if(bigDecimal.intValue()<=30000 || bigDecimal.intValue()>=70000){
                                    System.out.printf("%s %.2f%n",d.getName(),bigDecimal);
                                }
                            });

        entityManager.getTransaction().commit();
    }
}
