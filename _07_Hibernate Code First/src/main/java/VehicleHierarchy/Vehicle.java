package VehicleHierarchy;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected long id;

    @Basic
    protected String type;

    @Basic
    protected String model;

    @Basic
    protected BigDecimal price;

    @Column(name = "fuel_type")
    protected String fuelType;


    protected Vehicle(String type) {
        this.type = type;

    }
    protected Vehicle() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}

