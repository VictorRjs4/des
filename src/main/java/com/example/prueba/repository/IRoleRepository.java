package com.example.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba.seguridad.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

}