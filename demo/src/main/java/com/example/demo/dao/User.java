package com.example.demo.dao;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "users")
@Data
public class User {
    //TODO: почему плохо генерировать ламбуком хэшкод и иквалс
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private String id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String enabled;

    @Column
    private String email;

    @Column
    private Date lastActivity;

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
