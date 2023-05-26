package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.dto.ImportXmlPictureDto;
import softuni.exam.domain.entities.dto.ImportXmlPicturesDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.ValidatorUtilImpl;
import softuni.exam.util.XmlReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {
    private final static String XML_PICTURE_PATH = "Exams/Football-Info_Java DB Advanced Exam - 28 Jul 2019/src/main/resources/files/xml/pictures.xml";

    private final PictureRepository pictureRepository;
    private final ValidatorUtilImpl validatorUtilImpl;
    private final XmlReader xmlReader;
    private final ModelMapper mapper;

    public PictureServiceImpl(PictureRepository pictureRepository, ValidatorUtilImpl validatorUtilImpl, XmlReader xmlReader, ModelMapper mapper) {
        this.pictureRepository = pictureRepository;
        this.validatorUtilImpl = validatorUtilImpl;
        this.xmlReader = xmlReader;
        this.mapper = mapper;
    }

    @Override
    public String importPictures() {
        ImportXmlPicturesDto importXmlPicturesDto = this.xmlReader.parseXml(ImportXmlPicturesDto.class, readPicturesXmlFile());
        List<String> output = new ArrayList<>();
        for (ImportXmlPictureDto picture : importXmlPicturesDto.getPictures()) {
            if(!validatorUtilImpl.isValid(picture)){
                output.add("Invalid picture");
                continue;
            }
            Optional<Picture> optionalPicture = this.pictureRepository.findByUrl(picture.getUrl());
            if(optionalPicture.isPresent()){
                output.add("Invalid picture");
                continue;
            }
            Picture pictureToPersist = this.mapper.map(picture, Picture.class);
            Picture save = this.pictureRepository.save(pictureToPersist);
            output.add(String.format("Successfully imported picture - %s",picture.getUrl()));
        }

        return String.join("\n", output);
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() {
        try {
            return Files.readString(Path.of(XML_PICTURE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
