package com.example.demo.repository;

import com.example.demo.dao.Studio;
import com.example.demo.dao.StudioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, StudioId> {
}
