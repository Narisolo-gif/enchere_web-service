package com.enchere.controller;

import com.enchere.modele.Categorie;
import com.enchere.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategorieController {
    private final CategorieService service;

    @Autowired
    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public List<Categorie> getCategories() {
        return this.service.getCategories();
    }

    @GetMapping("/categories/{categorieid}")
    private Categorie getCategorie(@PathVariable("categorieid") Long categorieid)
    {
        return this.service.getCategorieById(categorieid);
    }

    @PostMapping("/categories")
    private Long saveCategorie(@RequestBody Categorie categorie)
    {
        this.service.saveOrUpdate(categorie);
        return categorie.getId();
    }

    @PutMapping("/categories")
    private Categorie updateCategorie(@RequestBody Categorie categorie)
    {
        this.service.saveOrUpdate(categorie);
        return categorie;
    }

    @DeleteMapping("/categories/{categorieid}")
    private void deleteCategorie(@PathVariable("categorieid") Long categorieid)
    {
        this.service.delete(categorieid);
    }
}
