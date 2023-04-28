import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        final String townName = new Scanner(System.in).nextLine();

        entityManager.getTransaction().begin();
        Town singleResult = entityManager.createQuery("FROM Town where name LIKE ?1", Town.class)
                .setParameter(1, townName).getSingleResult();
        List<Address> addresses = entityManager.createQuery("FROM Address a where a.town = ?1", Address.class)
                .setParameter(1, singleResult).getResultList();

        int num = addresses.size();
        addresses.forEach(a->{
            a.getEmployees().forEach(e->e.setAddress(null));
            a.setTown(null);
            entityManager.remove(a);
        });
        entityManager.remove(singleResult);
        System.out.printf("%d address in %s deleted",num,townName);
        entityManager.getTransaction().commit();
    }
}
