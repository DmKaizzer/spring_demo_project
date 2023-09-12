package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "studios")
@Data
public class Studio {
    @Id
    @Column(name = "studio_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "masters_of_studios",
        joinColumns = @JoinColumn(name = "studio_id"),
        inverseJoinColumns = @JoinColumn(name = "login"))
    private List<Master> masters;
}
