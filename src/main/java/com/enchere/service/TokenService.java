package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Token;
import com.enchere.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TokenService {

    private final TokenRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TokenService(TokenRepository repository) {
        this.repository = repository;
    }

    public List<Token> getTokens() {
        return this.repository.findAll();
    }

    public Token getTokenById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("token n'existe pas avec l'id :" + id));
    }

    public Token getTokenByIdUtilisateur(long id) {
        return this.repository.getByIdUtilisateur(id);
    }

    public void saveOrUpdate(Token token) {
        this.repository.save(token);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }

}
