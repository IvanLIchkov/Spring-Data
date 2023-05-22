package exam.model.Laptop;

import exam.model.Shop.Shop;

import java.math.BigDecimal;

public class TheBestLaptopDto {

    private String macAddress;
    private double cpuSpeed;
    private short ram;
    private short storage;
    private BigDecimal price;
    private Shop shop;

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public void setRam(short ram) {
        this.ram = ram;
    }

    public void setStorage(short storage) {
        this.storage = storage;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
       return String.format("Laptop - %s\n" +
                "*Cpu speed - %.2f\n" +
                "**Ram - %d\n" +
                "***Storage - %d\n" +
                "****Price - %.2f\n" +
                "#Shop name - %s\n" +
                "##Town - %s\n",this.macAddress,
                this.cpuSpeed,
                this.ram,
                this.storage,
                this.price,
                this.shop.getName(),
                this.shop.getTown().getName());
    }
}
