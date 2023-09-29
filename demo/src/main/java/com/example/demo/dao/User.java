package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "users")
@Data
public class User {
    //TODO: почему плохо генерировать ламбуком хэшкод и иквалс
    //TODO: outer join
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    private String username;

    @Column
    private String password;

    @Column
    private Boolean enabled;

    @Column
    private String email;

    @Column(name = "last_activity")
    private String lastActivity;

    @Column
    private Integer priority;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
