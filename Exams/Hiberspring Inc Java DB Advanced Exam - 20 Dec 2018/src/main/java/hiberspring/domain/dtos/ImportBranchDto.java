package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;

public class ImportBranchDto {

    @NotNull
    private String name;

    @NotNull
    private String town;

    public ImportBranchDto() {
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }
}
