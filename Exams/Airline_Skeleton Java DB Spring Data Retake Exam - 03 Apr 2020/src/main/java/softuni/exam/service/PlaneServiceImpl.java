package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Plane;
import softuni.exam.models.dto.plane.ImportPlaneDto;
import softuni.exam.models.dto.plane.ImportPlanesDto;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final static String URL_PLANES_XML = "Exams/Airline_Skeleton Java DB Spring Data Retake Exam - 03 Apr 2020/src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    public PlaneServiceImpl(PlaneRepository planeRepository, XmlParser xmlParser, ModelMapper mapper, ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count()>0;
    }

    @Override
    public String readPlanesFileContent() {
        try {
            return Files.readString(Path.of(URL_PLANES_XML));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importPlanes() {
        ImportPlanesDto importPlanesDto = this.xmlParser.parseXml(ImportPlanesDto.class, readPlanesFileContent());
        StringBuilder output = new StringBuilder();
        for (ImportPlaneDto plane : importPlanesDto.getPlanes()) {
            if(!validationUtil.isValid(plane)){
                output.append("Invalid plane").append("\n");
                continue;
            }
            Plane planeToPersist = this.mapper.map(plane, Plane.class);
            this.planeRepository.save(planeToPersist);
            output.append(String.format("Successfully imported Plane %s",plane.getRegisterNumber())).append("\n");
        }

        return String.valueOf(output);
    }
}
