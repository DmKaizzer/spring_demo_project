package com.example.demo.dao;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class StudioId implements Serializable {
    public String title;
    public String address;

    public StudioId() {

    }
}
