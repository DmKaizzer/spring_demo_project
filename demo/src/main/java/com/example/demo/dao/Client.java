package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, updatable = false)
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "id")
    private User username;

    @Column
    private Integer age;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Master master;

    @Override
    public String toString() {
        return "test";
    }
}
