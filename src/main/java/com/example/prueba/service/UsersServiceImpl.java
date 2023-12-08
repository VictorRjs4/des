package com.example.prueba.service;

import com.example.prueba.repository.IRoleRepository;
import com.example.prueba.repository.IUsersRepository;
import com.example.prueba.service.IUsersService;
import com.example.prueba.seguridad.Users;
import com.example.prueba.seguridad.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private IUsersRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void insert(Users user) {
        Users objUser = user;
        objUser.setPassword(passwordEncoder.encode(objUser.getPassword()));

        Role role = new Role();
        // Se asigna el rol con el que queremos que el usuario se cree
        role.setAuthority("ROLE_USER");
        role = roleRepository.save(role);

        objUser.getRoles().add(role);
        objUser = userRepository.save(objUser);
    }

}