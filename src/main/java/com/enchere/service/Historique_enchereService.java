package com.enchere.service;

import com.enchere.modele.Historique_enchere;
import com.enchere.repository.Historique_enchereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Historique_enchereService {
    private final Historique_enchereRepository repository;

    @Autowired
    public Historique_enchereService(Historique_enchereRepository repository) {
        this.repository = repository;
    }

    public List<Historique_enchere> getAll() {
        return this.repository.findAll();
    }

    public List<Historique_enchere> getHistorique(long idEnchere) {

        return this.repository.findByIdEnchere(idEnchere);
    }
    public Historique_enchere lastHistorique(long idEnchere) {

        return this.repository.lastHistorique(idEnchere);
    }
    public void saveOrUpdate(Historique_enchere enchere) {
        this.repository.save(enchere);
    }
}
