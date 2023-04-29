package VehicleHierarchy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle{

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    public Plane() {
        super("PLANE");
    }

    public Plane(String model, String fuelType, int passengerCapacity){
        this();
        this.model = model;
        this.fuelType = fuelType;
        this.passengerCapacity = passengerCapacity;
    }

}
