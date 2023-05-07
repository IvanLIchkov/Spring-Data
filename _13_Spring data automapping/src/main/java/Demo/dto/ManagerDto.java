package Demo.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private Set<EmployeeDto> subordinates;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubordinates(Set<EmployeeDto> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        String employees = this.subordinates
                .stream()
                .map(EmployeeDto::toString)
                .map(s -> "   - "+ s)
                .collect(Collectors.joining("\n"));
        return String.format("%s %s | Employees: %d%n%s%n",firstName,lastName,this.subordinates.size(),employees);
    }
}
