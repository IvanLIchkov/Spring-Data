package GringottsDatabase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class MagicWand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = Wizard.class,mappedBy = "wand")
    @JoinColumn
    private Wizard wizard;

    @Column
    private int size;

}
