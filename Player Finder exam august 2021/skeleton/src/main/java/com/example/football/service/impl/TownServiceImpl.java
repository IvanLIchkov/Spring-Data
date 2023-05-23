package com.example.football.service.impl;

import com.example.football.models.dto.ImportTownsDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final static String JSON_TOWNS_PATH = "/Users/scopi/Desktop/Spring-Data/Player Finder exam august 2021/skeleton/src/main/resources/files/json/towns.json";
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(JSON_TOWNS_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        ImportTownsDto[] townsDtos = this.gson.fromJson(readTownsFileContent(), ImportTownsDto[].class);

        List<String> result = new ArrayList<>();
        for (ImportTownsDto townDto : townsDtos) {
            Set<ConstraintViolation<ImportTownsDto>> validationErrors = this.validator.validate(townDto);
            if(!validationErrors.isEmpty()){
                result.add("Invalid Town");
                continue;
            }
            Optional<Town> townByName = this.townRepository.findTownByName(townDto.getName());
            if(townByName.isPresent()){
                result.add("Invalid Town");
                continue;
            }
            Town townToPersist = this.mapper.map(townDto, Town.class);
            this.townRepository.save(townToPersist);
            result.add(String.format("Successfully imported Town %s - %d%n",townDto.getName(), townDto.getPopulation()));
        }

        return String.join("\n", result);
    }
}
