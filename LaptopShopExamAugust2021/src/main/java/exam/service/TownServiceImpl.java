package exam.service;

import exam.model.Town.ImportTownsDto;
import exam.model.Town.Town;
import exam.repository.TownRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private static final Path XML_TOWNS_PATH = Path.of("/Users/scopi/Downloads/LaptopShopExamAugust2021/src/main/resources/files/xml/towns.xml");
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() >0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(XML_TOWNS_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        List<String> result = new ArrayList<>();

        JAXBContext jaxbContext = JAXBContext.newInstance(ImportTownsDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ImportTownsDto towns = (ImportTownsDto) unmarshaller.unmarshal(new FileReader(XML_TOWNS_PATH.toAbsolutePath().toString()));

        towns.getTowns()
                .forEach(t -> {
                    if(t.isValid()){
                        Optional<Town> optTown = this.townRepository.findByName(t.getName());
                        if(optTown.isPresent()){
                            result.add("Town already exists");
                        }else{
                            Town toPersist = this.mapper.map(t, Town.class);
                            this.townRepository.save(toPersist);
                            result.add("Saved Town - "+ t.getName());
                        }
                    }else{
                        result.add("Invalid Town");
                    }
                });

        return String.join("\n", result);
    }
}
