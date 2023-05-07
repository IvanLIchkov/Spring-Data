package com.example._13_springdataautomapping;

import com.example._13_springdataautomapping.entities.Employee;
import com.example._13_springdataautomapping.entities.dto.CustomDto;
import com.example._13_springdataautomapping.entities.dto.EmployeeDto;
import com.example._13_springdataautomapping.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//       this.persist();
        ModelMapper mapper = new ModelMapper();

        TypeMap<Employee, CustomDto> employeeToCustom = mapper.typeMap(Employee.class, CustomDto.class);
        employeeToCustom.addMappings(map -> map.<Integer>map(source -> source.getManager() == null ? 0 : source.getManager().getLastName().length(),
                (destination, value) -> destination.setManagerLastNameLength(value)));

//         this.employeeService.findEmployeesBornBefore(1990).forEach(System.out::println);

        List<Employee> all = this.employeeService.findAll();

        all.stream()
                .map(e -> employeeToCustom.map(e))
                .forEach(System.out::println);

//        EmployeeDto dto = mapper.map(managr, EmployeeDto.class);
//
//        System.out.println(dto);
    }

    private void persist() {
        Employee manager = new Employee("Mrs.", "Manager", BigDecimal.ONE, LocalDate.now(),null);

        Employee firstE = new Employee("first", "last", BigDecimal.TEN, LocalDate.now(),manager);
        this.employeeService.save(firstE);

        manager = this.employeeService.findOneById(firstE.getManager().getId())
                .get();

        Employee secondE = new Employee("second", "last", BigDecimal.ZERO, LocalDate.now(),manager);
//        this.employeeService.save(secondE);

    }
}
