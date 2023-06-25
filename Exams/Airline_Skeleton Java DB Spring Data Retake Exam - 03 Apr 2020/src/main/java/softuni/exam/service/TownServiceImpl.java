package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Town;
import softuni.exam.models.dto.ImportTownDto;
import softuni.exam.repository.TownRepository;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@Service
public class TownServiceImpl implements TownService {

    private static final String JSON_TOWN_PATH = "Exams/Airline_Skeleton Java DB Spring Data Retake Exam - 03 Apr 2020/src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper mapper;

    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, Gson gson, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() {
        try {
            return Files.readString(Path.of(JSON_TOWN_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importTowns() {
        ImportTownDto[] importTownDtos = this.gson.fromJson(readTownsFileContent(), ImportTownDto[].class);
        StringBuilder output = new StringBuilder();
        for (ImportTownDto importTownDto : importTownDtos) {
            if(!this.validationUtil.isValid(importTownDto)){
                output.append("Invalid Town").append("\n");
                continue;
            }
            Optional<Town> townByName = this.townRepository.findTownByName(importTownDto.getName());
            if(townByName.isPresent()){
                output.append("Invalid Town").append("\n");
                continue;
            }
            Town townToPersist = this.mapper.map(importTownDto, Town.class);
            output.append(String.format("Successfully imported Town %s - %d",importTownDto.getName(),importTownDto.getPopulation())).append("\n");
            this.townRepository.save(townToPersist);
        }
        return String.valueOf(output);
    }
}
