package exam.repository;

import exam.model.Shop.Shop;
import exam.model.Town.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//ToDo:
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByName(String name);
}
