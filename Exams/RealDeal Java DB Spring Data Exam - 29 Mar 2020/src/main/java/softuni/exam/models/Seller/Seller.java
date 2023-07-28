package softuni.exam.models.Seller;

import org.hibernate.validator.constraints.Length;
import softuni.exam.models.Offer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Table(name = "sellers")
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "first_name")
    @Length(min = 2, max = 20)
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @Length(min = 2, max = 20)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private Rating rating;

    @Column(nullable = false)
    private String town;


    public Seller() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

}
