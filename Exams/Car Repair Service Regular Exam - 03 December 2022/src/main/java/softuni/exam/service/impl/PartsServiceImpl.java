package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportPartsDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Implement all methods
@Service
public class PartsServiceImpl implements PartsService {

    private static final String PARTS_JSON_PATH = "/Users/scopi/Desktop/Spring-Data/Exams/Car Repair Service Regular Exam - 03 December 2022/src/main/resources/files/json/parts.json";

    private PartsRepository partsRepository;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
    private Gson gson;

    @Autowired
    public PartsServiceImpl(PartsRepository partsRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
        this.partsRepository = partsRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_JSON_PATH));
    }

    @Override
    public String importParts() throws IOException {
        ImportPartsDto[] parts = this.gson.fromJson(readPartsFileContent(), ImportPartsDto[].class);
        StringBuilder output = new StringBuilder();
        for (ImportPartsDto part : parts) {
            if(this.partsRepository.findPartByPartName(part.getPartName()).isPresent()){
                output.append("Invalid part").append("\n");
                continue;
            }
            if(this.validationUtil.isValid(part)){
                Part partToPersist = this.mapper.map(part, Part.class);
                this.partsRepository.save(partToPersist);
                output.append(String.format("Successfully imported part %s - %s",part.getPartName(), part.getPrice())).append("\n");
                continue;
            }
            output.append("Invalid part").append("\n");
        }

        return String.valueOf(output).trim();
    }
}
