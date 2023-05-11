package com.example.cardealer.entities.supplier;

import com.example.cardealer.entities.part.Part;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany
    private Set<Part> parts;

    public Supplier() {
       this.parts = new HashSet<>();
    }

    public Supplier(String name, boolean isImporter) {
        this();
        this.name = name;
        this.isImporter = isImporter;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
