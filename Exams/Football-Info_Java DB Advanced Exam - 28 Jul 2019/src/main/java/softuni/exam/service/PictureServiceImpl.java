package softuni.exam.service;

import org.springframework.stereotype.Service;
import softuni.exam.repository.PictureRepository;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final XmlR

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String importPictures() {
        //TODO Implement me
        return "";
    }

    @Override
    public boolean areImported() {
        //TODO Implement me
        return false;
    }

    @Override
    public String readPicturesXmlFile() {
        //TODO Implement me
        return "";
    }

}
