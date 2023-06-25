package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Plane;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {
        Optional<Plane> getPlaneByRegisterNumber(String registerNumber);
}
