package com.enchere.controller;

import com.enchere.modele.Solde;
import com.enchere.service.SoldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SoldeController {
    private final SoldeService service;

    @Autowired
    public SoldeController(SoldeService service) {
        this.service = service;
    }

    @GetMapping("/soldes")
    public List<Solde> getSoldes() {
        return this.service.getSoldes();
    }

    @GetMapping("/soldes/{soldeid}")
    private Solde getSolde(@PathVariable("soldeid") Long soldeid)
    {
        return this.service.getSoldeById(soldeid);
    }

    @PostMapping("/soldes")
    private Long saveSolde(@RequestBody Solde solde)
    {
        this.service.saveOrUpdate(solde);
        return solde.getId();
    }

    @PutMapping("/soldes")
    private Solde updateSolde(@RequestBody Solde solde)
    {
        this.service.saveOrUpdate(solde);
        return solde;
    }

    @DeleteMapping("/soldes/{soldeid}")
    private void deleteSolde(@PathVariable("soldeid") Long soldeid)
    {
        this.service.delete(soldeid);
    }
}
