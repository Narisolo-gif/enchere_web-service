package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Blocage_compte;
import com.enchere.repository.Blocage_compteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Blocage_compteService {

    private final Blocage_compteRepository repository;

    @Autowired
    public Blocage_compteService(Blocage_compteRepository repository) {
        this.repository = repository;
    }

    public List<Blocage_compte> getBlocage_comptes() {
        return this.repository.findAll();
    }

    public Blocage_compte getBlocage_compteById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("blocage_compte n'existe pas avec l'id :" + id));
    }
    public void deleteBlocage(long idEnchere, long idUtilisateur) {
        List<Long> id=this.repository.deleteBlocage(idEnchere,idUtilisateur);
        for(int i=0;i<id.size();i++){
            this.repository.deleteById(id.get(i));
        }
        //this.repository.deleteBlocage(idEnchere,idUtilisateur);
    }

    public void saveOrUpdate(Blocage_compte blocage_compte) {
        this.repository.save(blocage_compte);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }

}
