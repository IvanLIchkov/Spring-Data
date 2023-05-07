package Demo.entities;

import Demo.dto.EmployeeDto;
import Demo.dto.ManagerDto;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        Address address = new Address("boris 3", 3, "sofia", "bulgaria");
        Employee manager = new Employee("Mr.", "manager", BigDecimal.TEN, LocalDate.now(),address, true);

        Employee firstE = new Employee("Mr.", "Employee First", BigDecimal.ONE, LocalDate.now(),address, true);

        Employee secondE = new Employee("Mr.", "Employee Second", BigDecimal.ZERO, LocalDate.now(),address, true);

        manager.addEmployee(firstE);
        manager.addEmployee(secondE);

        ManagerDto managerDto = modelMapper.map(manager, ManagerDto.class);

        System.out.println(managerDto);
    }
}
