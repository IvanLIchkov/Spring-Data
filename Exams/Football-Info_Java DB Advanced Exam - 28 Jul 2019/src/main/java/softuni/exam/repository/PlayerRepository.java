package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player1;

import java.util.Optional;

@Repository
public interface PlayerRepository  extends JpaRepository<Player1, Integer> {

    Optional<Player1> findByFirstNameAndLastNameAndNumber(String firstName, String lastName, int number);
}
