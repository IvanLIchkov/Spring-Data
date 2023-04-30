package entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BillingDetails(User owner) {
        this.owner = owner;
    }
    public BillingDetails(){}

    @OneToOne
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
