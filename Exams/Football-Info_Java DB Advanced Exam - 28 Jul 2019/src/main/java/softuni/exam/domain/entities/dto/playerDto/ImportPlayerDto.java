package softuni.exam.domain.entities.dto.playerDto;

import org.hibernate.validator.constraints.Length;
import softuni.exam.domain.entities.dto.teamDto.ImportTeamDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ImportPlayerDto {

    @NotNull
    private String firstName;

    @Length(min = 3, max = 15)
    @NotNull
    private String lastName;

    @NotNull
    @Min(1)
    @Max(99)
    private int number;

    @NotNull
    @Min(0)
    private BigDecimal salary;

    @NotNull
    @Length(min = 2, max = 2)
    private String position;

    @NotNull
    private ImportPictureUrlDto picture;

    @NotNull
    private ImportJsonTeamDto team;

    public ImportPlayerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public ImportPictureUrlDto getPicture() {
        return picture;
    }

    public ImportJsonTeamDto getTeam() {
        return team;
    }


}
