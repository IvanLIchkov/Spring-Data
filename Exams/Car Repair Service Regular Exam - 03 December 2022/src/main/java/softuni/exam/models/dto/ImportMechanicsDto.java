package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ImportMechanicsDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 2)
    private String firstName;

    @NotNull
    @Length(min = 2)
    private String lastName;

    @Length(min = 2)
    @Nullable
    private String phone;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
}
