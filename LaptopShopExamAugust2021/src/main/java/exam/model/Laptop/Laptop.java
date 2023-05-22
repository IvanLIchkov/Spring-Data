package exam.model.Laptop;

import exam.model.Shop.Shop;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mac_address", unique = true)
    private String macAddress;

    @Column(name = "cpu_speed")
    private double cpuSpeed;

    @Column
    private short ram;

    @Column
    private short storage;

    @Column(length = 1000)
    private String description;

    @Column
    private BigDecimal price;

    @Column(name = "warranty_type")
    private int warrantyType;

    @ManyToOne
    private Shop shop;

    public Laptop() {
    }

    public Laptop(String macAddress, double cpuSpeed, short ram, short storage, String description, BigDecimal price, int warrantyType, Shop shop) {
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warrantyType = warrantyType;
        this.shop = shop;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public short getRam() {
        return ram;
    }

    public void setRam(short ram) {
        this.ram = ram;
    }

    public short getStorage() {
        return storage;
    }

    public void setStorage(short storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = WarrantyType.valueOf(warrantyType).ordinal();
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
