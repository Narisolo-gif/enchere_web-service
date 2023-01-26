package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Comission;
import com.enchere.repository.ComissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComissionService {

    private final ComissionRepository repository;

    @Autowired
    public ComissionService(ComissionRepository repository) {
        this.repository = repository;
    }

    public List<Comission> getComissions() {
        return this.repository.findAll();
    }

    public Comission getComissionById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("comission n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Comission comission) {
        this.repository.save(comission);
    }
    
    public void delete(String id) {
        this.repository.deleteById(id);
    }

}
