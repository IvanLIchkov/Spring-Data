package VehicleHierarchy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle{

    public Bike() {
        super("BIKE");
    }
}
