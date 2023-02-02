package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Solde;
import com.enchere.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SoldeService {

    private final SoldeRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SoldeService(SoldeRepository repository) {
        this.repository = repository;
    }

    public List<Solde> getSoldes() {
        return this.repository.findAll();
    }

    public Solde getSoldeById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("solde n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Solde solde) {
        this.repository.save(solde);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }
}
