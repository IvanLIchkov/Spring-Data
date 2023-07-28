package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCarXml;
import softuni.exam.models.dto.ImportCarsXml;
import softuni.exam.models.dto.ImportPartsDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Implement all methods
@Service
public class CarsServiceImpl implements CarsService {
    private static String CARS_FILE_PATH = "/Users/scopi/Desktop/Spring-Data/Exams/Car Repair Service Regular Exam - 03 December 2022/src/main/resources/files/xml/cars.xml";

    private CarsRepository carsRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private XmlParser xmlParser;
    private ModelMapper mapper;

    public CarsServiceImpl(CarsRepository carsRepository, FileUtil fileUtil, ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper mapper) {
        this.carsRepository = carsRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        ImportCarsXml importCarsXml = this.xmlParser.parseXml(ImportCarsXml.class, readCarsFromFile());
        StringBuilder output = new StringBuilder();

        for (ImportCarXml car : importCarsXml.getCars()) {
            output.append(System.lineSeparator());
            if(this.carsRepository.findCarByPlateNumber(car.getPlateNumber()).isPresent()){
                output.append("Invalid car");
                continue;
            }
            if(!this.validationUtil.isValid(car)){
                output.append("Invalid car");
                continue;
            }
            Car carToPersist = this.mapper.map(car, Car.class);
            this.carsRepository.save(carToPersist);
            output.append(String.format("Successfully imported car %s - %s",car.getCarMake(),car.getCarModel()));
        }

        return String.valueOf(output).trim();
    }
}
