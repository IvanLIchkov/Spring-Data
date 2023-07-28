package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportMechanicsDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Implement all methods
@Service
public class MechanicsServiceImpl implements MechanicsService {

    private static final String MECHANIC_IMPORT_JSON = "/Users/scopi/Desktop/Spring-Data/Exams/Car Repair Service Regular Exam - 03 December 2022/src/main/resources/files/json/mechanics.json";

    private MechanicsRepository mechanicsRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
    private Gson gson;

    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
        this.mechanicsRepository = mechanicsRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANIC_IMPORT_JSON));
    }

    @Override
    public String importMechanics() throws IOException {

        ImportMechanicsDto[] importMechanicsDtos = this.gson.fromJson(readMechanicsFromFile(), ImportMechanicsDto[].class);
        StringBuilder output = new StringBuilder();
        for (ImportMechanicsDto importMechanicDto : importMechanicsDtos) {
            if(this.mechanicsRepository.findMechanicByEmail(importMechanicDto.getEmail()).isPresent()){
                output.append("Invalid mechanic").append(System.lineSeparator());
                continue;
            }
            if(!this.validationUtil.isValid(importMechanicDto)){
                output.append("Invalid mechanic").append(System.lineSeparator());
                continue;
            }
            Mechanic mechanicToPersist = this.mapper.map(importMechanicDto, Mechanic.class);
            this.mechanicsRepository.save(mechanicToPersist);
            output.append(String.format("Successfully imported mechanic %s %s",importMechanicDto.getFirstName(), importMechanicDto.getLastName())).append(System.lineSeparator());
        }

        return String.valueOf(output).trim();
    }
}
