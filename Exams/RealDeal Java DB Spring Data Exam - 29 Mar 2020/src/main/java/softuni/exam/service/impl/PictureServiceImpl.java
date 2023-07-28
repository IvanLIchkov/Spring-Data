package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car.dto.ImportCarsDto;
import softuni.exam.models.Picture.Picture;
import softuni.exam.models.Picture.dto.ImportPictureDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_JSON_URL = "/Users/scopi/Desktop/Spring-Data/Exams/RealDeal Java DB Spring Data Exam - 29 Mar 2020/src/main/resources/files/json/pictures.json";

    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private final Gson gson;

    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository, ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() {
        try {
            return Files.readString(Path.of(PICTURE_JSON_URL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importPictures() throws IOException {
        ImportPictureDto[] importPictureDtos = this.gson.fromJson(readPicturesFromFile(), ImportPictureDto[].class);
        List<String> output = new ArrayList<>();
        for (ImportPictureDto importPictureDto : importPictureDtos) {
            if(!validationUtil.isValid(importPictureDto)){
                output.add("Invalid Picture");
                continue;
            }
            Picture pictureToPersist = this.mapper.map(importPictureDto, Picture.class);
            System.out.println();
        }


        return null;
    }
}
