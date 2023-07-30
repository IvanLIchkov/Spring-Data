package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import java.util.List;
import java.util.Optional;

// TODO:
@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findStarByName(String name);

    Optional<Star> findById(long id);

    List<Star> findAllByStarTypeAndObserversEmptyOrderByLightYearsAsc(String starType);
}
