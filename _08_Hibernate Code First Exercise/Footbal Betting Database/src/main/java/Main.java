import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Football_Betting").createEntityManager();

        entityManager.getTransaction().begin();
        Color blue = new Color("Blue");
        Color red = new Color("Red");

        Continent europe = new Continent("Europe");
        Continent africa = new Continent("Africa");

        entityManager.persist(europe);
        entityManager.persist(africa);

        Country bulgaria = new Country("BUL","Bulgaria");
        europe.addCountry(bulgaria);

        Country nigeria = new Country("NIG","Nigeria");
        africa.addCountry(nigeria);

        Country romania = new Country("ROM", "Romania");
        europe.addCountry(romania);

        Town sofia =  new Town("Sofia");

        Town plovdiv =new Town("Plovdiv");

        Town bukuresht =new Town("Bucharesht");
        Town  sibiu =new Town("Sibiu");
        Town  platew = new Town("Plateu");


        Team botev = new Team("Botev","B","BTV",blue,red,plovdiv,2000);
        Team loko = new Team("Lokomotiv","L","LKP",blue,red,plovdiv,300000);
        Team levski = new Team("Levski","L","LEV",blue,red,sofia,1200000);
        entityManager.persist(botev);
        entityManager.persist(loko);
        entityManager.persist(levski);

        entityManager.getTransaction().commit();
    }
}
