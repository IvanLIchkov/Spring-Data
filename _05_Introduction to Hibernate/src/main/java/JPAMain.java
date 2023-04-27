import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school-db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

//        Student student = new Student("Test");
//        entityManager.persist(student);

        Student found = entityManager.find(Student.class, 1L);

        entityManager.remove(found);
        System.out.println(found.getId() + " "+ found.getName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
