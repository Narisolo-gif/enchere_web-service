package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Demande_rechargement;
import com.enchere.modele.Solde;
import com.enchere.repository.Demande_rechargementRepository;
import com.enchere.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Demande_rechargementService {

    private final Demande_rechargementRepository repository;
    private final SoldeRepository soldeRepository;

    @Autowired
    public Demande_rechargementService(Demande_rechargementRepository repository, SoldeRepository soldeRepository) {
        this.repository = repository;
        this.soldeRepository = soldeRepository;
    }

    public List<Demande_rechargement> getDemande_rechargements() {
        return this.repository.findAll();
    }

    public List<Demande_rechargement> getDemande_rechargementsByStatut(long statut) {
        return this.repository.getByStatut(statut);
    }

    public Demande_rechargement getDemande_rechargementById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("demande_rechargement n'existe pas avec l'id :" + id));
    }

    public void valider(long iddemande) {
        this.repository.valider(iddemande);
    }

    public void saveOrUpdate(Demande_rechargement demande_rechargement) {
        this.repository.save(demande_rechargement);
        Solde solde = new Solde();
        solde.setUtilisateur(demande_rechargement.getUtilisateur());
        solde.setMontant(demande_rechargement.getMontant());
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        solde.setDate(new Timestamp(date.getTime()));
        this.soldeRepository.save(solde);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }

}
