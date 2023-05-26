package softuni.exam.domain.entities.dto.playerDto;

import javax.validation.constraints.NotNull;

public class ImportPictureUrlDto {

    @NotNull
    private String url;

    public ImportPictureUrlDto() {
    }

    public String getUrl() {
        return url;
    }
}
