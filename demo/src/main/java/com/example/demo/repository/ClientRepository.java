package com.example.demo.repository;

import com.example.demo.dao.Client;
import com.example.demo.dao.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> getClientsByMaster(Master master);
}
