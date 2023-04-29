package VehicleHierarchy;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends PassengerVehicle{



    public Car(){
        super("CAR");
    }

    public Car(String model, String fuelType,int seats) {
        this();
        this.model = model;
        this.fuelType = fuelType;
        this.seats = seats;
    }

}

