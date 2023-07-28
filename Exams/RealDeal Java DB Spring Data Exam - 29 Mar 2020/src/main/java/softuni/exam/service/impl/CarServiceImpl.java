package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car.Car;
import softuni.exam.models.Car.dto.ImportCarsDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {

    private static final String CARS_JSON_URL = "/Users/scopi/Desktop/Spring-Data/Exams/RealDeal Java DB Spring Data Exam - 29 Mar 2020/src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper, ValidationUtil validationUtil, Gson gson) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() {
        try {
            return Files.readString(Path.of(CARS_JSON_URL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importCars() throws IOException {
        ImportCarsDto[] importCarsDtos = this.gson.fromJson(readCarsFileContent(), ImportCarsDto[].class);
        StringBuilder output = new StringBuilder();
        for (ImportCarsDto importCarDto : importCarsDtos) {
            if(!validationUtil.isValid(importCarDto)){
                output.append("Invalid car").append("\n");
                continue;
            }
            Car carToPersist = this.mapper.map(importCarDto, Car.class);
            carToPersist.setKilometers(importCarDto.getKilometres());
            output.append(String.format("Successfully imported car - %s - %s",importCarDto.getMake(),importCarDto.getModel())).append("\n");
            this.carRepository.save(carToPersist);
        }

        return String.valueOf(output);
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
