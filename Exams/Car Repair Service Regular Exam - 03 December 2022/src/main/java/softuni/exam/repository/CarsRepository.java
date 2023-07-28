package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Car;

import java.util.List;
import java.util.Optional;

// TODO:
@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByPlateNumber(String plateNumber);

    Optional<Car> findCarById(long id);


}
