package com.enchere.controller;

import com.enchere.modele.TokenAdmin;
import com.enchere.utils.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.enchere.service.AdminService;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
    private final AdminService service;

    @Autowired
    public AdminController(AdminService services) {
        this.service = services;
    }

    @PostMapping("/admins/login")
    public TokenAdmin logAdmin(@RequestBody Login l){
        return this.service.login(l.getEmail(), l.getMdp());
    }


    /*@PostMapping("/admins/login")
    public TokenAdmin logAdmin(@RequestParam("email") String email, @RequestParam("mdp") String mdp){
        return this.service.login(email, mdp);
    }*/
}
