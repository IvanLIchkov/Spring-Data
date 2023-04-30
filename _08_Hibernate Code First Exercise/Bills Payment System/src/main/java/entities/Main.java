package entities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("bills_db").createEntityManager();

        entityManager.getTransaction().begin();
            User user = new User("Ivan", "Lichkov", "vako@", "1234");
            CreditCard creditCard = new CreditCard("Kreeditna","2022", "2023",user);
            BankAccount bankAcoount = new BankAccount("spestoven","1234567", user);
            user.setBankAccount(bankAcoount);
            user.setCreditCard(creditCard);
            entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}