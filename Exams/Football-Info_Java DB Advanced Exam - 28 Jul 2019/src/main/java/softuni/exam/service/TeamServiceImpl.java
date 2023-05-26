package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.domain.entities.dto.teamDto.ImportTeamDto;
import softuni.exam.domain.entities.dto.teamDto.ImportTeamsDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtilImpl;
import softuni.exam.util.XmlReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Path XML_TEAMS_PATH = Path.of("Exams/Football-Info_Java DB Advanced Exam - 28 Jul 2019/src/main/resources/files/xml/teams.xml");

    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper mapper;
    private final ValidatorUtilImpl validatorUtil;
    private final XmlReader xmlReader;

    public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository, ModelMapper mapper, ValidatorUtilImpl validatorUtil, XmlReader xmlReader) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.mapper = mapper;
        this.validatorUtil = validatorUtil;
        this.xmlReader = xmlReader;
    }

    @Override
    public String importTeams() {
        ImportTeamsDto importTeamsDto = xmlReader.parseXml(ImportTeamsDto.class, readTeamsXmlFile());
        List<String> output = new ArrayList<>();
        for (ImportTeamDto team : importTeamsDto.getTeams()) {
            if(!validatorUtil.isValid(team)){
                output.add("Invalid team");
                continue;
            }
            Optional<Picture> optionalPicture = this.pictureRepository.findByUrl(team.getPicture().getUrl());
            Optional<Team> optionalTeam = this.teamRepository.findByName(team.getName());
            if(optionalTeam.isPresent() || optionalPicture.isEmpty()){
                output.add("Invalid team");
                continue;
            }
            Team teamToPersist = this.mapper.map(team, Team.class);
            teamToPersist.setPicture(optionalPicture.get());
            this.teamRepository.save(teamToPersist);
            output.add(String.format("Successfully imported - %s",team.getName()));
        }

        return String.join("\n", output);
    }

    @Override
    public boolean areImported() {
        //TODO Implement me
        return this.teamRepository.count() >0;
    }

    @Override
    public String readTeamsXmlFile(){
        //TODO Implement me
        try {
            return Files.readString(XML_TEAMS_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
