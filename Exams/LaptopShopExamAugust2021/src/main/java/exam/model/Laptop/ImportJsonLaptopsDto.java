package exam.model.Laptop;

import java.math.BigDecimal;
import java.util.Arrays;

public class ImportJsonLaptopsDto {

    private String macAddress;
    private double cpuSpeed;
    private short ram;
    private short storage;
    private String description;
    private BigDecimal price;
    private String warrantyType;
    private ImportShopNameDto shop;

    public ImportJsonLaptopsDto() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public short getRam() {
        return ram;
    }

    public short getStorage() {
        return storage;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public ImportShopNameDto getShop() {
        return shop;
    }

    public boolean isValidLaptop(){
        if(this.macAddress.length()<=8){
            return false;
        }
        if(cpuSpeed<0){
            return false;
        }
        if(this.ram<8 || this.ram>128){
            return false;
        }
        if(this.storage<128 || this.storage>1024){
            return false;
        }
        if(this.description.length()<10){
            return false;
        }
        if(Arrays.stream(WarrantyType.values()).noneMatch(war -> war.name().equals(warrantyType))){
            return false;
        }
        return this.price.compareTo(BigDecimal.valueOf(0)) >= 0;
    }
}
