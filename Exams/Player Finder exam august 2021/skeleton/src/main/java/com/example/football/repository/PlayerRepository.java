package com.example.football.repository;

import com.example.football.models.dto.ExportBestPlayerDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ToDo:
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    @Query("select new com.example.football.models.dto.ExportBestPlayerDto(concat(p.firstName, ' ', p.lastName), p.position, p.team.name, p.team.stadiumName) from Player p " +
            "join Stat s on s = p.stat " +
            "where p.birthDate >'1995-01-01' and p.birthDate < '2003-01-01' " +
            "order by s.shooting DESC, s.passing DESC , s.endurance DESC, p.lastName ")
    List<ExportBestPlayerDto> findBestPlayers();
}
