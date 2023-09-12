package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User username;

    @Column
    private String authority;

    @Override
    public String toString() {
        return "test";
    }


}
