package exam.model.Shop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class ImportShopDto {

    @XmlElement
    private String address;

    @XmlElement(name = "employee-count")
    private int employeeCount;

    @XmlElement
    private BigDecimal income;

    @XmlElement
    private String name;

    @XmlElement(name = "shop-area")
    private int shopArea;

    @XmlElement(name = "town")
    private ImportTownNameDto town;

    public ImportShopDto() {
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public ImportTownNameDto getTown() {
        return town;
    }
    public boolean isValidShop(){
        if(this.name.length()<4){
            return false;
        }
        if(this.income.compareTo(BigDecimal.valueOf(20000))<0){
            return false;
        }
        if(this.address.length()<4){
            return false;
        }
        if(this.employeeCount<1 || this.employeeCount>50){
            return false;
        }
        return this.shopArea >= 150;
    }
}
