package people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Teacher extends BasePerson {

    @Column
    private String email;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;

    @OneToMany
    private Set<Course> courses;

    public Teacher(){
        super();
        this.courses = new HashSet<>();
    }
}
