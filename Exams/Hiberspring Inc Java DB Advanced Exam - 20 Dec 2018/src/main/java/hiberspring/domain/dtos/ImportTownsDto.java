package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;

public class ImportTownsDto {

    @NotNull
    private String name;
    @NotNull
    private int population;

    public ImportTownsDto() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
