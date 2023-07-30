package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronautDto.ImportAstronomerDto;
import softuni.exam.models.dto.AstronautDto.ImportAstronomersDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Implement all methods
@Service
public class AstronomerServiceImpl implements AstronomerService {

    private static String ASTRONOMERS_FILE_PATH = "src/main/resources/files/xml/astronomers.xml";

    private AstronomerRepository astronomerRepository;
    private StarRepository starRepository;

    private ValidationUtil validationUtil;
    private XmlParser xmlParser;
    private ModelMapper mapper;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper mapper) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(ASTRONOMERS_FILE_PATH));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        ImportAstronomersDto importAstronomersDto = this.xmlParser.parseXml(ImportAstronomersDto.class, readAstronomersFromFile());
        StringBuilder output = new StringBuilder();
        for (ImportAstronomerDto astronomerDto : importAstronomersDto.getAstronomerDtos()) {

            Optional<Star> star = this.starRepository.findById(astronomerDto.getObservingStarId());
            output.append(System.lineSeparator());

            if(this.astronomerRepository.findByFirstNameAndLastName(astronomerDto.getFirstName(), astronomerDto.getLastName()).isPresent()
                    || !this.validationUtil.isValid(astronomerDto)
                    || star.isEmpty()){
                output.append("Invalid astronomer");
                continue;
            }

            Astronomer astronomerToPersist = this.mapper.map(astronomerDto, Astronomer.class);
            astronomerToPersist.setObservingStar(star.get());
            this.astronomerRepository.save(astronomerToPersist);
            output.append(String.format("Successfully imported astronomer %s %s - %.2f",astronomerDto.getFirstName(), astronomerDto.getLastName(), astronomerDto.getAverageObservationHours()));
        }

        return String.valueOf(output).trim();
    }
}
