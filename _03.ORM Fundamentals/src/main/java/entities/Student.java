package entities;

import orm.anotations.Column;
import orm.anotations.Entity;
import orm.anotations.Id;

@Entity(name = "students")
public class Student {
    @Id
    private int id;
    @Column(name = "name")
    private String name;

    public Student(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
