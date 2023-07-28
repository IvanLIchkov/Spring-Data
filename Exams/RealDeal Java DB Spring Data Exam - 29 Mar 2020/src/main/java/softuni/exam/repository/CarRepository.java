package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Car.Car;

//ToDo
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
