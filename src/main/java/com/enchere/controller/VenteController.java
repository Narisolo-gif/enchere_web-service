package com.enchere.controller;

import com.enchere.modele.Vente;
import com.enchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VenteController {
    public final VenteService service;

    @Autowired
    public VenteController(VenteService service) { this.service = service; }

    @GetMapping("/ventes")
    public List<Vente> getVentes() {
        return this.service.getVentes();
    }

    @GetMapping("/ventes/benefice")
    public double getBeneficeTotal() {
        return this.service.getBenefice();
    }

    @GetMapping("/utilisateurs/{idutilisateur}/ventes")
    public List<Vente> getVentesByIdUtilisateur(@PathVariable("idutilisateur") Long idutilisateur) {
        return this.service.getVentesByIdUtilisateur(idutilisateur);
    }

    @PostMapping("/ventes")
    private void saveVente(@RequestBody Vente vente)
    {
        this.service.save(vente);
    }

    @PutMapping("/ventes")
    private Vente updateVente(@RequestBody Vente vente)
    {
        this.service.update(vente);
        return vente;
    }
}
