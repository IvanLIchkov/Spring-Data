import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni")
                .createEntityManager();

        entityManager.getTransaction().begin();
        List<Address> resultList = entityManager.createQuery("from Address a order by a.employees.size desc",Address.class).setMaxResults(10).getResultList();
        resultList.forEach(r-> System.out.printf("%s %s - %d employees%n",r.getText(),r.getTown().getName(),r.getEmployees().size()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
