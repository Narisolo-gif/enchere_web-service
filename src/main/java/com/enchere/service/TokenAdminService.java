package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.TokenAdmin;
import com.enchere.repository.TokenAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenAdminService {

    private final TokenAdminRepository repository;

    @Autowired
    public TokenAdminService(TokenAdminRepository repository) {
        this.repository = repository;
    }

    public List<TokenAdmin> getTokens() {
        return this.repository.findAll();
    }

    public TokenAdmin getTokenById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("token n'existe pas avec l'id :" + id));
    }

    public TokenAdmin getTokenByIdAdmin(long id) {
        return this.repository.getByIdAdmin(id);
    }

    public void saveOrUpdate(TokenAdmin token) {
        this.repository.save(token);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }

}
