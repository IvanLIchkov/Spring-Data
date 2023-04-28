import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FindLatest10Projects {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery("FROM Project p order by p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted((p2,p1)->p2.getName().compareTo(p1.getName()))
                .collect(Collectors.toList()).forEach(p-> System.out.printf("Project name:%s%n        " +
                        "Project Description: %s%n        " +
                        "Project Start Date:%s%n        " +
                        "Project End Date: %s%n",p.getName(),p.getDescription(),p.getStartDate().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss:ms")),p.getEndDate()));
        entityManager.getTransaction().commit();
    }
}
