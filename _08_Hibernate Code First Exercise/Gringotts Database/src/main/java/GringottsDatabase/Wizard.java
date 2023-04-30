package GringottsDatabase;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wizzards")
public class Wizard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false ,length = 60)
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private Integer age;

    @OneToMany
    private List<Deposit> deposits;

    @OneToOne
    @JoinColumn
    private MagicWand wand;

    public Wizard(){
        this.deposits = new ArrayList<>();
    }


}
