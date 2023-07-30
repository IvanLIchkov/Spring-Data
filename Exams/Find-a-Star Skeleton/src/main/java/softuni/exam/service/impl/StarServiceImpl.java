package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportStarDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class StarServiceImpl implements StarService {

    private static String STARS_FILE_PATH = "src/main/resources/files/json/stars.json";

    private StarRepository starRepository;
    private ConstellationRepository constellationRepository;

    private ValidationUtil validationUtil;
    private ModelMapper mapper;
    private Gson gson;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(STARS_FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
        ImportStarDto[] importStarDtos = this.gson.fromJson(readStarsFileContent(), ImportStarDto[].class);
        StringBuilder output = new StringBuilder();

        for (ImportStarDto importStarDto : importStarDtos) {

            output.append(System.lineSeparator());

            if(!validationUtil.isValid(importStarDto)){
                output.append("Invalid star");
                continue;
            }
            if (this.starRepository.findStarByName(importStarDto.getName()).isPresent()){
                output.append("Invalid star");
                continue;
            }

            Star startToPersist = this.mapper.map(importStarDto, Star.class);
            Constellation constellation = this.constellationRepository.findById(importStarDto.getConstellation()).get();
            startToPersist.setConstellation(constellation);
            this.starRepository.save(startToPersist);
            output.append(String.format("Successfully imported star %s - %.2f light years",importStarDto.getName(), importStarDto.getLightYears()));
        }

        return String.valueOf(output).trim();
    }

    @Override
    public String exportStars() {

        List<Star> allByStarTypeAndObserversEmptyOrderByLightYearsAsc = this.starRepository.findAllByStarTypeAndObserversEmptyOrderByLightYearsAsc(String.valueOf(StarType.RED_GIANT));
        List<String> output = allByStarTypeAndObserversEmptyOrderByLightYearsAsc.stream().map(Star::toString).collect(Collectors.toList());
        return String.join("\n", output);
    }
}
