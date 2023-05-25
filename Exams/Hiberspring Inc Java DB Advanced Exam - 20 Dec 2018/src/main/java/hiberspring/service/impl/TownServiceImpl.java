package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.ImportTownsDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private static final String JSON_TOWNS_PATH = "/Users/scopi/Desktop/Spring-Data/Exams/Hiberspring Inc Java DB Advanced Exam - 20 Dec 2018/src/main/resources/files/towns.json";

    private final FileUtil fileUtil;
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(FileUtil fileUtil, TownRepository townRepository, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile()  {
        return fileUtil.readFile(JSON_TOWNS_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        ImportTownsDto[] townsDtos = this.gson.fromJson(townsFileContent, ImportTownsDto[].class);

        StringBuilder output = new StringBuilder();

        for (ImportTownsDto townDto : townsDtos) {

            if(!validationUtil.isValid(townDto)){
                output.append(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Town townToPersist = this.mapper.map(townDto, Town.class);
            this.townRepository.save(townToPersist);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE+"%n","Town", townDto.getName()));
        }
        return output.toString();
    }
}
