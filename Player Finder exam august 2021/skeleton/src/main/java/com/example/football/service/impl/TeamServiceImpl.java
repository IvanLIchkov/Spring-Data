package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamsDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

//ToDo - Implement all methods
@Service
public class TeamServiceImpl implements TeamService {

    private final static String JSON_TEAMS_PATH = "/Users/scopi/Desktop/Spring-Data/Player Finder exam august 2021/skeleton/src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.mapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() {
        try {
            return Files.readString(Path.of(JSON_TEAMS_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importTeams() {
        ImportTeamsDto[] teamsDtos = gson.fromJson(readTeamsFileContent(), ImportTeamsDto[].class);
        StringBuilder result = new StringBuilder();

        for (ImportTeamsDto teamDto : teamsDtos) {
            Set<ConstraintViolation<ImportTeamsDto>> validate = this.validator.validate(teamDto);
            if(!validate.isEmpty()){
                result.append("Invalid Team\n");
                continue;
            }
            Optional<Team> optionalTeam = this.teamRepository.findTeamByName(teamDto.getName());
            if(optionalTeam.isPresent()){
                result.append("Invalid Team\n");
                continue;
            }
            Team teamToPersist = this.mapper.map(teamDto, Team.class);
            Optional<Town> townByName = this.townRepository.findTownByName(teamDto.getTownName());
            teamToPersist.setTown(townByName.get());
            this.teamRepository.save(teamToPersist);
            result.append(String.format("Successfully imported Team %s - %d%n",teamDto.getName(), teamDto.getFanBase()));

        }


        return result.toString();
    }
}
