package softuni.exam.domain.entities;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotNull
    private String url;

    public Picture() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //TODO
}
