package softuni.exam.service;


import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
@Service
public interface CarsService {

    boolean areImported();

    String readCarsFromFile() throws IOException;

    String importCars() throws IOException, JAXBException;
}
