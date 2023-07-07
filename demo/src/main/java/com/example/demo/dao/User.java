package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class User {
    //TODO: почему плохо генерировать ламбуком хэшкод и иквалс
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String login;

    @Column
    private String password;

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "id: " +
                "login: " +
                "password: ";
    }

}
