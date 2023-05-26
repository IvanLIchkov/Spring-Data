package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player1;
import softuni.exam.domain.entities.Team;
import softuni.exam.domain.entities.dto.playerDto.ExportPlayerDto;
import softuni.exam.domain.entities.dto.playerDto.ExportPlayerWithSalaryDto;
import softuni.exam.domain.entities.dto.playerDto.ImportPlayerDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtilImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final static Path JSON_PLAYERS_PATH = Path.of("Exams/Football-Info_Java DB Advanced Exam - 28 Jul 2019/src/main/resources/files/json/players.json");

    private final PlayerRepository playerRepository;
    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;
    private final ValidatorUtilImpl validatorUtil;
    private final ModelMapper mapper;
    private final Gson gson;

    public PlayerServiceImpl(PlayerRepository playerRepository, PictureRepository pictureRepository, TeamRepository teamRepository, ValidatorUtilImpl validatorUtil, ModelMapper mapper, Gson gson) {
        this.playerRepository = playerRepository;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
        this.validatorUtil = validatorUtil;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public String importPlayers() {
        ImportPlayerDto[] importPlayerDtos = this.gson.fromJson(readPlayersJsonFile(), ImportPlayerDto[].class);
        List<String> output = new ArrayList<>();
        for (ImportPlayerDto playerDto : importPlayerDtos) {
            if(!validatorUtil.isValid(playerDto)){
                output.add("Invalid player");
                continue;
            }
            Optional<Picture> optionalPlayerPicture = this.pictureRepository.findByUrl(playerDto.getPicture().getUrl());
            Optional<Picture> optionalTeamPicture = this.pictureRepository.findByUrl(playerDto.getTeam().getPicture().getUrl());
            Optional<Team> optionalTeam = this.teamRepository.findByName(playerDto.getTeam().getName());
            Optional<Player1> optionalPlayer = this.playerRepository.findByFirstNameAndLastNameAndNumber(playerDto.getFirstName(), playerDto.getLastName(), playerDto.getNumber());
            if(optionalPlayer.isPresent() || optionalPlayerPicture.isEmpty() || optionalTeam.isEmpty() || optionalTeamPicture.isEmpty()){
                output.add("Invalid player");
                continue;
            }
            Player1 playerToPersist = this.mapper.map(playerDto, Player1.class);
            playerToPersist.setPicture(optionalPlayerPicture.get());
            playerToPersist.setTeam(optionalTeam.get());
            this.playerRepository.save(playerToPersist);
            output.add(String.format("Successfully imported player: %s",playerDto.getFirstName()+" "+playerDto.getLastName()));
        }

        return String.join("\n", output);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile()  {
        try {
            return Files.readString(JSON_PLAYERS_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        List<ExportPlayerWithSalaryDto> players = this.playerRepository.findAllBySalaryAfterOrderBySalaryDesc(BigDecimal.valueOf(10000));

        List<String> output = players.stream().map(ExportPlayerWithSalaryDto::toString).collect(Collectors.toList());

        return String.join("\n", output);
    }

    @Override
    public String exportPlayersInATeam() {
        String teamName = "North Hub";
        List<ExportPlayerDto> players = this.playerRepository.findAllByTeamNameOrderById(teamName);
//        StringBuilder output = new StringBuilder(String.format("Team: %s%n",teamName));

        List<String> output = new ArrayList<>();
        output.add(String.format("Team: %s%n",teamName));

        for (ExportPlayerDto player : players) {
            output.add(player.toString());
        }

        return String.join("",output);
    }
}
