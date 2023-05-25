package hiberspring.service.impl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.ExportEmployeeDto;
import hiberspring.domain.dtos.ImportEmployeeDto;
import hiberspring.domain.dtos.ImportEmployeesDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String XML_EMPLOYEES_PATH = "Exams/Hiberspring Inc Java DB Advanced Exam - 20 Dec 2018/src/main/resources/files/employees.xml";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository, XmlParser xmlParser, ModelMapper mapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() {
        return fileUtil.readFile(XML_EMPLOYEES_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException {
        ImportEmployeesDto importEmployeeDto = this.xmlParser.parseXml(ImportEmployeesDto.class, readEmployeesXmlFile());
        List<String> output = new ArrayList<>();

        for (ImportEmployeeDto employee : importEmployeeDto.getEmployees()) {
            if(!validationUtil.isValid(employee)){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Optional<Employee> optionalEmployee = this.employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
            Optional<EmployeeCard> optionalEmployeeCard = this.employeeCardRepository.findByNumber(employee.getCardNumber());
            Optional<Branch> optionalBranch = this.branchRepository.findByName(employee.getBranchName());

            if(optionalEmployee.isPresent() || optionalEmployeeCard.isEmpty() || optionalBranch.isEmpty()){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Employee employeeToPersist = this.mapper.map(employee, Employee.class);
            employeeToPersist.setEmployeeCard(optionalEmployeeCard.get());
            employeeToPersist.setBranch(optionalBranch.get());
            this.employeeRepository.save(employeeToPersist);
            output.add(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"Employee", employee.getFirstName()+" " + employee.getLastName()));
        }


        return String.join("\n", output);
    }

    @Override
    public String exportProductiveEmployees() {
        List<ExportEmployeeDto> exportEmployeeDtos = this.employeeRepository.exportProductiveEmployee();
        List<String> collect = exportEmployeeDtos.stream().map(ExportEmployeeDto::toString).collect(Collectors.toList());
        return String.join("\n", collect);
    }
}
