package com.enchere.controller;

import com.enchere.modele.Token;
import com.enchere.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TokenController {
    private final TokenService service;

    @Autowired
    public TokenController(TokenService service) {
        this.service = service;
    }

    @GetMapping("/tokens")
    public List<Token> getTokens() {
        return this.service.getTokens();
    }

    @GetMapping("/tokens/{tokenid}")
    private Token getToken(@PathVariable("tokenid") Long tokenid)
    {
        return this.service.getTokenById(tokenid);
    }

    @GetMapping("/tokens/utilisateurs/{utilisateurid}")
    private Token getTokenByIdUtilisateur(@PathVariable("utilisateurid") Long utilisateurid)
    {
        return this.service.getTokenByIdUtilisateur(utilisateurid);
    }

    @PostMapping("/tokens")
    private Long saveToken(@RequestBody Token token)
    {
        this.service.saveOrUpdate(token);
        return token.getId();
    }

    @PutMapping("/tokens")
    private Token updateToken(@RequestBody Token token)
    {
        this.service.saveOrUpdate(token);
        return token;
    }

    @DeleteMapping("/tokens/{tokenid}")
    private void deleteToken(@PathVariable("tokenid") Long tokenid)
    {
        this.service.delete(tokenid);
    }
    @DeleteMapping("/tokens")
    private void deleteToken(@PathVariable("tokenid") Long tokenid)
    {
        this.service.deleteAll();
    }
}
