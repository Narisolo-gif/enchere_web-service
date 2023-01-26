package com.enchere.controller;

import com.enchere.modele.Blocage_compte;
import com.enchere.service.Blocage_compteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)

public class Blocage_compteController {
    private final Blocage_compteService service;

    @Autowired
    public Blocage_compteController(Blocage_compteService service) {
        this.service = service;
    }

    @GetMapping("/blocage_comptes")
    public List<Blocage_compte> getBlocage_comptes() {
        return this.service.getBlocage_comptes();
    }

    @GetMapping("/blocage_comptes/{blocage_compteid}")
    private Blocage_compte getBlocage_compte(@PathVariable("blocage_compteid") Long blocage_compteid)
    {
        return this.service.getBlocage_compteById(blocage_compteid);
    }

    @PostMapping("/blocage_comptes")
    private Long saveBlocage_compte(@RequestBody Blocage_compte blocage_compte)
    {
        this.service.saveOrUpdate(blocage_compte);
        return blocage_compte.getId();
    }

    @PutMapping("/blocage_comptes")
    private Blocage_compte updateBlocage_compte(@RequestBody Blocage_compte blocage_compte)
    {
        this.service.saveOrUpdate(blocage_compte);
        return blocage_compte;
    }

    @DeleteMapping("/blocage_comptes/{blocage_compteid}")
    private void deleteBlocage_compte(@PathVariable("blocage_compteid") Long blocage_compteid)
    {
        this.service.delete(blocage_compteid);
    }

}
