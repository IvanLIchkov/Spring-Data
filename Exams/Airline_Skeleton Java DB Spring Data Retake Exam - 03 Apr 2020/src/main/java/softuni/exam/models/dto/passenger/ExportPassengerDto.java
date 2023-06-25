package softuni.exam.models.dto.passenger;

public class ExportPassengerDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private long numberOfTickets;

    public ExportPassengerDto(String firstName, String lastName, String email, String phoneNumber, long numberOfTickets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.numberOfTickets = numberOfTickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getNumberOfTickets() {
        return numberOfTickets;
    }

    @Override
    public String toString() {
        return String.format("Passenger %s %s\n" +
                "       Email - %s\n" +
                "       Phone - %s\n" +
                "       Number of tickets - %d\n",this.firstName,
                this.lastName,
                this.email,
                this.phoneNumber,
                this.numberOfTickets);
    }
}
