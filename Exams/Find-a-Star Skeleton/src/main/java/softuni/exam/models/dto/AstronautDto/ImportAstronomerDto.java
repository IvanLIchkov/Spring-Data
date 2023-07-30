package softuni.exam.models.dto.AstronautDto;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import softuni.exam.util.DateAdapterXml;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


//•	id - accepts integer values, a primary identification field, an auto incremented field.
//        •	first name - accepts char sequence (between 2 to 30 inclusive).
//        •	last name - accepts char sequence (between 2 to 30 inclusive).
//        •	salary - accepts number values that are more than or equal to 15000.00.
//        •	averageObservationHours - accepts number values that are more than 500.00.
//        •	birthday - a date in the "yyyy-MM-dd" format. Can be nullable.
//        •	observing star - the current star that the astronomer is studying.
//        •	Constraint: The astronomers table has a relation with stars table.


@XmlRootElement(name = "astronomer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportAstronomerDto {

    @XmlElement(name = "average_observation_hours")
    @NotNull
    @Min(500)
    private double averageObservationHours;

    @XmlElement(name = "birthday")
    @Nullable
    @XmlJavaTypeAdapter(DateAdapterXml.class)
    private LocalDate birthday;

    @XmlElement(name = "first_name")
    @NotNull
    @Length(min = 2, max = 30)
    private String firstName;

    @XmlElement(name = "last_name")
    @NotNull
    @Length(min = 2, max = 30)
    private String lastName;

    @XmlElement(name = "salary")
    @NotNull
    @Min(15000)
    private double salary;

    @XmlElement(name = "observing_star_id")
    @NotNull
    private long observingStarId;

    public ImportAstronomerDto() {
    }

    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    @Nullable
    public LocalDate getBirthday() {
        return birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public long getObservingStarId() {
        return observingStarId;
    }
}
