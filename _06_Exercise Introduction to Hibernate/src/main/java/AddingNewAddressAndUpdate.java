import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class AddingNewAddressAndUpdate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String lastName = new Scanner(System.in).nextLine();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        entityManager.getTransaction().begin();
          entityManager.persist(newAddress);
         entityManager.createQuery("UPDATE Employee e set e.address = ?1 where e.lastName LIKE ?2")
                .setParameter(1,newAddress)
                .setParameter(2,lastName)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
