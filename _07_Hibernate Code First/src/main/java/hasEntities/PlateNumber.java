package hasEntities;

import javax.persistence.*;

@Entity
@Table(name = "plates_numbers")
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    @OneToOne(targetEntity = HasTruck.class, mappedBy = "plateNumber")
    private HasTruck hasTruck;

    public PlateNumber(){};
}
