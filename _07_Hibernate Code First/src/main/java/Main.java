import VehicleHierarchy.Bike;
import VehicleHierarchy.Car;
import VehicleHierarchy.Vehicle;
import hasEntities.Article;
import hasEntities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("relations").createEntityManager();
        entityManager.getTransaction().begin();

//        Vehicle car = new Car("Ford Focus", "Petrol", 5);
//        Vehicle bike = new Bike();

//        entityManager.persist(car);
//        entityManager.persist(bike);

            Article article = new Article("alabal");
        User author = new User("Toshko");

        author.addArticle(article);
        article.setAuthor(author);
        entityManager.persist(author);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
