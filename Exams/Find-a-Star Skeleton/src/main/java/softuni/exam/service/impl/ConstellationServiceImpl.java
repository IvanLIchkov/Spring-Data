package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportConstellationDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Implement all methods
@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static String CONSTELLATION_FILE_PATH = "src/main/resources/files/json/constellations.json";

    private ConstellationRepository constellationRepository;

    private ValidationUtil validationUtil;
    private ModelMapper mapper;
    private Gson gson;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
        this.constellationRepository = constellationRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(CONSTELLATION_FILE_PATH));
    }

    @Override
    public String importConstellations() throws IOException {
        ImportConstellationDto[] importConstellationDtos = this.gson.fromJson(readConstellationsFromFile(), ImportConstellationDto[].class);
        StringBuilder output = new StringBuilder();
        for (ImportConstellationDto importConstellationDto : importConstellationDtos) {

            output.append(System.lineSeparator());

            if (!validationUtil.isValid(importConstellationDto)){
                output.append("Invalid constellation");
                continue;
            }

            if(this.constellationRepository.findByName(importConstellationDto.getName()).isPresent()){
                output.append("Invalid constellation");
                continue;
            }

            Constellation constellationToPersist = this.mapper.map(importConstellationDto, Constellation.class);
            output.append(String.format("Successfully imported constellation %s - %s",importConstellationDto.getName(), importConstellationDto.getDescription()));
            this.constellationRepository.save(constellationToPersist);
        }

        return String.valueOf(output).trim();
    }
}
