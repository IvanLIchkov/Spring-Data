package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.ImportEmployeeCardDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private static final String JSON_EMPLOYEE_CARDS_PATH = "Exams/Hiberspring Inc Java DB Advanced Exam - 20 Dec 2018/src/main/resources/files/employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper mapper;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, ModelMapper mapper) {
        this.employeeCardRepository = employeeCardRepository;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return fileUtil.readFile(JSON_EMPLOYEE_CARDS_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {

        ImportEmployeeCardDto[] employeeCardDtos = this.gson.fromJson(employeeCardsFileContent, ImportEmployeeCardDto[].class);

        List<String> output = new ArrayList<>();

        for (ImportEmployeeCardDto employeeCardDto : employeeCardDtos) {
            if(!validationUtil.isValid(employeeCardDto)){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Optional<EmployeeCard> employeeCard = this.employeeCardRepository.findByNumber(employeeCardDto.getNumber());
            if(employeeCard.isPresent()){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            EmployeeCard cardToPersist = this.mapper.map(employeeCardDto, EmployeeCard.class);
            this.employeeCardRepository.save(cardToPersist);
            output.add(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"Employee Card",employeeCardDto.getNumber()));
        }

        return String.join("\n", output);
    }
}
