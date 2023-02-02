package com.enchere.controller;

import com.enchere.modele.Demande_rechargement;
import com.enchere.service.Demande_rechargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Demande_rechargementController {
    private final Demande_rechargementService service;

    @Autowired
    public Demande_rechargementController(Demande_rechargementService service) {
        this.service = service;
    }

    @GetMapping("/demande_rechargements")
    public List<Demande_rechargement> getDemande_rechargements() {
        return this.service.getDemande_rechargements();
    }

    @GetMapping("/demande_rechargements/statut/{statut}")
    public List<Demande_rechargement> getDemande_rechargementsByStatut(@PathVariable("statut") Long statut) {
        return this.service.getDemande_rechargementsByStatut(statut);
    }

    @GetMapping("/demande_rechargements/{demande_rechargementid}")
    private Demande_rechargement getDemande_rechargement(@PathVariable("demande_rechargementid") Long demande_rechargementid)
    {
        return this.service.getDemande_rechargementById(demande_rechargementid);
    }

    @PostMapping("/demande_rechargements")
    private Long saveDemande_rechargement(@RequestBody Demande_rechargement demande_rechargement)
    {
        this.service.saveOrUpdate(demande_rechargement);
        return demande_rechargement.getId();
    }

    @PutMapping("/demande_rechargements")
    private Demande_rechargement updateDemande_rechargement(@RequestBody Demande_rechargement demande_rechargement)
    {
        this.service.saveOrUpdate(demande_rechargement);
        return demande_rechargement;
    }

    @PutMapping("/demande_rechargements/valider/{iddemande}")
    private void updateDemande_rechargement(@PathVariable("iddemande") Long iddemande)
    {
        this.service.valider(iddemande);
    }

    @DeleteMapping("/demande_rechargements/{demande_rechargementid}")
    private void deleteDemande_rechargement(@PathVariable("demande_rechargementid") Long demande_rechargementid)
    {
        this.service.delete(demande_rechargementid);
    }

}
