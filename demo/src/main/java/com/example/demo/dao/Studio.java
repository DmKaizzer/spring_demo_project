package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "studios")
@Data
public class Studio {

    @EmbeddedId
    private StudioIdentification studioId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "masters_of_studios",
            joinColumns = {
                    @JoinColumn(table = "studios", name = "studio_address", referencedColumnName = "address"),
                    @JoinColumn(table = "studios", name = "studio_title", referencedColumnName = "title")
            },
            inverseJoinColumns = @JoinColumn(table = "masters", name = "master_id", referencedColumnName = "id"))
    private List<Master> masters;

    @Override
    public String toString() {
        return "test";
    }
}
