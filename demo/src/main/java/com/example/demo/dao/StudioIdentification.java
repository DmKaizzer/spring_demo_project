package com.example.demo.dao;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StudioIdentification implements Serializable {
    public String title;
    public String address;
}
