package com.example.wokshop.models.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegistrationDto {

    @Size(min = 2, message = "Username must be least 2 symbols long.")
    private String username;

    @Size(min = 6)
    private String password;

    private String confirmPassword;

    @Email
    private String email;

    public RegistrationDto() {
    }

    public RegistrationDto(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "RegistrationDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
