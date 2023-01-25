package com.enchere.controller;

import com.enchere.modele.Comission;
import com.enchere.service.ComissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ComissionController {
    private final ComissionService service;

    @Autowired
    public ComissionController(ComissionService service) {
        this.service = service;
    }

    @GetMapping("/comissions")
    public List<Comission> getComissions() {
        return this.service.getComissions();
    }

    @GetMapping("/comissions/{comissionid}")
    private Comission getComission(@PathVariable("comissionid") String comissionid)
    {
        return this.service.getComissionById(comissionid);
    }

    @PostMapping("/comissions")
    private String saveComission(@RequestBody Comission comission)
    {
        this.service.saveOrUpdate(comission);
        return comission.getId();
    }

    @PutMapping("/comissions")
    private Comission updateComission(@RequestBody Comission comission)
    {
        this.service.saveOrUpdate(comission);
        return comission;
    }

    @DeleteMapping("/comissions/{comissionid}")
    private void deleteComission(@PathVariable("comissionid") String comissionid)
    {
        this.service.delete(comissionid);
    }

}
