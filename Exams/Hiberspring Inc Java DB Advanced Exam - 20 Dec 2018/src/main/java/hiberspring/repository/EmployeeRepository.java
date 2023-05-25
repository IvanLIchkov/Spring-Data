package hiberspring.repository;

import hiberspring.domain.dtos.ExportEmployeeDto;
import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select new hiberspring.domain.dtos.ExportEmployeeDto(concat(e.firstName,' ',e.lastName),e.position, e.employeeCard.number) from Employee e " +
            "join Product p on e.branch = p.branch " +
            "where p is not null " +
            "group by e.id " +
            "order by concat(e.firstName,'', e.lastName) asc , length(e.position) asc")
    List<ExportEmployeeDto> exportProductiveEmployee();

}
