package com.example.demo.repository;

import com.example.demo.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jpaUserRepository")
public interface JpaUserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
