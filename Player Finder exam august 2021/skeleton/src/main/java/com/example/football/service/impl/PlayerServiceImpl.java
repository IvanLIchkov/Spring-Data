package com.example.football.service.impl;

import com.example.football.models.dto.ExportBestPlayerDto;
import com.example.football.models.dto.ImportPlayer.ImportPlayerDto;
import com.example.football.models.dto.ImportPlayer.ImportPlayersDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String XML_PLAYERS_PATH ="/Users/scopi/Desktop/Spring-Data/Player Finder exam august 2021/skeleton/src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;
    private final TeamRepository teamRepository;
    private final Validator validator;
    private final ModelMapper mapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, StatRepository statRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.teamRepository = teamRepository;
        this.mapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent()  {
        try {
            return Files.readString(Path.of(XML_PLAYERS_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importPlayers() throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(ImportPlayersDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportPlayersDto unmarshal = (ImportPlayersDto) unmarshaller.unmarshal(Files.newBufferedReader(Path.of(XML_PLAYERS_PATH)));

        StringBuilder output = new StringBuilder();

        for (ImportPlayerDto player : unmarshal.getPlayers()) {
            if(!this.validator.validate(player).isEmpty()){
                output.append("Invalid Player\n");
                continue;
            }
            Optional<Player> optionalPlayer = this.playerRepository.findByEmail(player.getEmail());
            if (optionalPlayer.isPresent()){
                output.append("Invalid Player\n");
                continue;
            }
            Optional<Town> town = this.townRepository.findTownByName(player.getTown().getName());
            Optional<Team> team = this.teamRepository.findTeamByName(player.getTeam().getName());
            Optional<Stat> stat = this.statRepository.findById(player.getStat().getId());

            Player playerToPersist = this.mapper.map(player, Player.class);
            playerToPersist.setTown(town.get());
            playerToPersist.setTeam(team.get());
            playerToPersist.setStat(stat.get());

            this.playerRepository.save(playerToPersist);
            output.append(String.format("Successfully imported Player %s %s - %s%n",player.getFirstName(),player.getLastName(),player.getPosition()));

        }


        return output.toString();
    }

    @Override
    public String exportBestPlayers() {
        List<String> collect = this.playerRepository.findBestPlayers().stream().map(ExportBestPlayerDto::toString).collect(Collectors.toList());
        return String.join("", collect);
    }
}
