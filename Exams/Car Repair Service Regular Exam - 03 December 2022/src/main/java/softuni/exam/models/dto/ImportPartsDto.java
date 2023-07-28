package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;


public class ImportPartsDto {

    @Length(min = 2, max = 19)
    @NotNull
    private String partName;

    @DecimalMin(value = "10.0")
    @DecimalMax(value = "2000.0")
    @NotNull
    private double price;

    @Positive
    @NotNull
    private int quantity;

    public String getPartName() {
        return partName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isValidPart(){
        if(partName.length()<2 || partName.length() >19){
            return false;
        }else if(price<10 || price >2000){
            return false;
        }else return quantity >= 0;
    }
}
