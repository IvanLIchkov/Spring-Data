package hiberspring.domain.dtos;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductDto {

    @NotNull
    @XmlAttribute(name = "name")
    private String name;

    @NotNull
    @XmlAttribute(name = "clients")
    private int clients;

    @XmlElement(name = "branch")
    @NotNull
    private String branchName;

    public ImportProductDto() {
    }

    public String getName() {
        return name;
    }

    public int getClients() {
        return clients;
    }

    public String getBranchName() {
        return branchName;
    }
}
