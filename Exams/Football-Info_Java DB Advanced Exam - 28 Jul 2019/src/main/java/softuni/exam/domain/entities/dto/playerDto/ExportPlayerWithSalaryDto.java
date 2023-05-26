package softuni.exam.domain.entities.dto.playerDto;

import softuni.exam.domain.entities.Team;

import java.math.BigDecimal;

public class ExportPlayerWithSalaryDto {
    private String firstName;
    private String lastName;
    private int number;
    private BigDecimal salary;
    private Team team;

    public ExportPlayerWithSalaryDto(String firstName, String lastName, int number, BigDecimal salary, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.salary = salary;
        this.team = team;
    }

    @Override
    public String toString() {
        return String.format("Player name: %s %s \n" +
                "       Number: %d\n" +
                "       Salary: %.2f\n" +
                "       Team: %s",this.firstName, this.lastName, this.number, this.salary,this.team.getName());
    }
}
