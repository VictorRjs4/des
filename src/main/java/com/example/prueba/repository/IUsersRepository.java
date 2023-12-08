package com.example.prueba.repository;

import com.example.prueba.seguridad.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}