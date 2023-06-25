package softuni.exam.models.dto.passenger;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ImportPassengerDto {

    @Length(min = 2)
    @NotNull
    private String firstName;

    @Length(min = 2)
    @NotNull
    private String lastName;

    @Positive
    private int age;

    @NotNull
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    private String town;

    public ImportPassengerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTown() {
        return town;
    }
}
