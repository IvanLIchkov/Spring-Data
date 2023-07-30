package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Constellation;

import java.util.Optional;

// TODO:
@Repository
public interface ConstellationRepository extends JpaRepository<Constellation, Long> {

    Optional<Constellation> findByName(String name);

    Optional<Constellation> findById(long id);

    @Query("select c from Constellation c where c.name =:name")
    Constellation find(String name);
}
