package softuni.exam.domain.entities.dto.playerDto;

public class ExportPlayerDto {
    private String firstName;
    private String lastName;
    private String position;
    private int number;

    public ExportPlayerDto(String firstName, String lastName, String position, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("          Player name: %s %s - %s\n" +
                "          Number: %d\n",this.firstName,this.lastName,this.position, this.number);
    }
}
