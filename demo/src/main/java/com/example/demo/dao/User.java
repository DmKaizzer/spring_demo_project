package com.example.demo.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
public class User {
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

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
