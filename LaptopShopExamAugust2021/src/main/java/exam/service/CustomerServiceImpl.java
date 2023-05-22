package exam.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.Customer.Customer;
import exam.model.Customer.ImportCustomersDto;
import exam.model.Town.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Path JSON_CUSTOMER_PATH = Path.of("/Users/scopi/Downloads/LaptopShopExamAugust2021/src/main/resources/files/json/customers.json");

    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        TypeMap<ImportCustomersDto, Customer> typeMap = this.mapper.createTypeMap(ImportCustomersDto.class, Customer.class);
        Converter<String, LocalDate> formatDate = ctx -> {
            String source = ctx.getSource();
            try {
                return formatDateToString(source);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        };
        typeMap.addMappings(mapper -> mapper.using(formatDate).map(ImportCustomersDto::getRegisteredOn, Customer::setRegistered));
    }
    private static LocalDate formatDateToString(String v) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(v!=null)
            return LocalDate.parse(v, dateFormat);
        else return null;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count()>0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return new String(Files.readAllBytes(JSON_CUSTOMER_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        FileReader fileReader = new FileReader(JSON_CUSTOMER_PATH.toFile());

        ImportCustomersDto[] customers = gson.fromJson(fileReader, ImportCustomersDto[].class);

        List<String> result = new ArrayList<>();
        for (ImportCustomersDto customer : customers) {
            if(!customer.isValidCustomer()){
                result.add("Invalid Customer");
                continue;
            }
            Optional<Customer> optionalCustomer = this.customerRepository.getByEmail(customer.getEmail());
            if(optionalCustomer.isPresent()){
                result.add("Invalid Customer");
                continue;
            }
            Optional<Town> optionalTown = this.townRepository.findByName(customer.getTown().getName());
            Customer customerToPersist = this.mapper.map(customer, Customer.class);
            customerToPersist.setTown(optionalTown.get());
            this.customerRepository.save(customerToPersist);
            result.add(String.format("Successfully imported Customer %s %s - %s",customerToPersist.getFirstName(), customerToPersist.getLastName(), customerToPersist.getEmail()));
        }

        return String.join("\n", result);
    }
}
