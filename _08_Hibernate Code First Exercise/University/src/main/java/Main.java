import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("university").createEntityManager();
    }
}