package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;

public class ImportEmployeeCardDto {

    @NotNull
    private String number;

    public ImportEmployeeCardDto() {
    }

    public String getNumber() {
        return number;
    }
}
