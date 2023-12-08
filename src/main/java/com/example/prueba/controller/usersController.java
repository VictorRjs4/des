package com.example.prueba.controller;

import com.example.prueba.repository.IUsersRepository;
import com.example.prueba.seguridad.Users;
import com.example.prueba.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/users")
public class usersController {

    @Autowired
    private IUsersService userService;
    @Autowired
    private IUsersRepository Users;

    @RequestMapping("/new")
    public String goNewUser(Model model) {
        model.addAttribute("user", new Users());
        return "users/user";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Users user, BindingResult binRes, Model model) throws ParseException {
        try {
            userService.insert(user);
            return "redirect:/login";
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return "users/user";
        }
    }
}
