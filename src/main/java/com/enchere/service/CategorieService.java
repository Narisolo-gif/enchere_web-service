package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Categorie;
import com.enchere.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CategorieService {

    private final CategorieRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CategorieService(CategorieRepository repository) {
        this.repository = repository;
    }

    public List<Categorie> getCategories() {
        return this.repository.findAll();
    }

    public Categorie getCategorieById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("categorie n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Categorie categorie) {
        this.repository.save(categorie);
    }
    public long getId() {
        return this.repository.getId();
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }

}
