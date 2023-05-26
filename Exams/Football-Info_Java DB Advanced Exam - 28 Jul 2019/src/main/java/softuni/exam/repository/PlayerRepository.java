package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player1;
import softuni.exam.domain.entities.dto.playerDto.ExportPlayerDto;
import softuni.exam.domain.entities.dto.playerDto.ExportPlayerWithSalaryDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository  extends JpaRepository<Player1, Integer> {

    Optional<Player1> findByFirstNameAndLastNameAndNumber(String firstName, String lastName, int number);

    List<ExportPlayerDto> findAllByTeamNameOrderById(String teamName);

    List<ExportPlayerWithSalaryDto> findAllBySalaryAfterOrderBySalaryDesc(BigDecimal salary);
}
