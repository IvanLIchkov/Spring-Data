package softuni.exam.domain.entities.dto.playerDto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ImportJsonTeamDto {

    @NotNull
    @Length(min = 3, max = 20)
    private String name;

    @NotNull
    private ImportPictureUrlDto picture;

    public ImportJsonTeamDto() {
    }

    public String getName() {
        return name;
    }

    public ImportPictureUrlDto getPicture() {
        return picture;
    }
}
