import entities.Student;
import entities.User;
import orm.Connector;
import orm.EntityManager;

import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connector.createConnection("root", "12345", "soft_uni");
        Connection connection = (Connection) Connector.getConnection();

        EntityManager<User> userManager = new EntityManager<User>(connection);
        User user = new User("First", 28, LocalDate.now());
        userManager.persist(user);

//        EntityManager<Student> studentEntityManager = new EntityManager<Student>(connection);
//        Student student = new Student("name");
//        studentEntityManager.persist(student);
    }
}