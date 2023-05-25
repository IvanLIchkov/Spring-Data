package exam.model.Customer;

import exam.model.Shop.ImportTownNameDto;

import java.util.Date;

public class ImportCustomersDto {

    private String firstName;
    private String lastName;
    private String email;
    private String registeredOn;
    private ImportTownNameDto town;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public ImportTownNameDto getTown() {
        return town;
    }

    public boolean isValidCustomer(){
        if(this.firstName.length()<2){
            return false;
        }
        if(this.lastName.length()<2){
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
}
