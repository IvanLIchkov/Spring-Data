package hiberspring.domain.dtos;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ImportEmployeeDto {

    @XmlAttribute(name = "first-name")
    @NotNull
    private String firstName;

    @XmlAttribute(name = "last-name")
    @NotNull
    private String lastName;

    @XmlAttribute(name = "position")
    @NotNull
    private String position;

    @XmlElement(name = "card")
    @NotNull
    private String cardNumber;

    @XmlElement(name = "branch")
    @NotNull
    private String branchName;

    public ImportEmployeeDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getBranchName() {
        return branchName;
    }
}
