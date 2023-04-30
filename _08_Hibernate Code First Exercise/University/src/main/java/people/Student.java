package people;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Student extends BasePerson{

    @Column(name = "average_grade")
    private double averageGrade;

    @Column
    private String attendance;

    @ManyToMany
    private List<Course> courses;

    public Student(){
        super();
        this.courses = new ArrayList<>();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
