package com.enchere.controller;

import com.enchere.modele.Enchere;
import com.enchere.modele.Enchere_photo;
import com.enchere.modele.Historique_enchere;
import com.enchere.service.EnchereService;
import com.enchere.service.Historique_enchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnchereController {
    private final EnchereService service;
    private final Historique_enchereService historique_enchereService;
    @Autowired
    public EnchereController(EnchereService service,Historique_enchereService hs) {

        this.service = service;
        this.historique_enchereService=hs;
    }

    @GetMapping("/encheres")
    public List<Enchere> getEncheres() {
        List<Enchere> val=this.service.getEncheres();
        /*for(int i=0;i<val.size();i++){
            val.get(i).getStatut();
        }*/
        return val;
    }
    @GetMapping("/encheres/{id}/historique")
    public List<Historique_enchere> getHistorique(@PathVariable("id") Long id) {
        List<Historique_enchere> val=this.historique_enchereService.getHistorique(id);
        return val;
    }
    @GetMapping("/encheres/{enchereid}")
    private Enchere getEnchere(@PathVariable("enchereid") Long enchereid)
    {
        return this.service.getEnchereById(enchereid);
    }
    /*@GetMapping("/encheres/idutilisateur/{id}")
    private List<Enchere> getEnchereByIdUser(@PathVariable("id") Long id)
    {
        return this.service.getEnchereByIdUser(id);
    }*/
    @GetMapping("/encheres/idutilisateur/{id}")
    private List<Enchere> recherche(@PathVariable("id") Long id)
    {
        return this.service.getEnchereByIdUser(id);
    }

    @GetMapping("/encheres/count")
    private int getNombre()
    {
        return this.service.getNombre();
    }

    @GetMapping("/encheres/encours")
    private List<Enchere> getEnchereByIdUser()
    {
        return this.service.getEnchereEnCours();
    }

    @GetMapping("/encheres/recheche")
    private List<Enchere> rechercheMulti(@RequestParam("prix_min")double prix_min,@RequestParam("prix_max")double prix_max,@RequestParam("mot_cle")String mot_cle,@RequestParam("categorie")Long idcategorie)
    {
        Long max=idcategorie;
        if(prix_max==0){
            prix_max=this.service.prixMax();
        }
        if(mot_cle==null){
            mot_cle="";
        }
        if(idcategorie==0){
            max=this.service.categorieMax();
        }
        return this.service.recherche(prix_min,prix_max,mot_cle,idcategorie,max);
    }

    @PostMapping("/encheres")
    private Long saveEnchere(@RequestBody Enchere enchere)
    {
        this.service.saveOrUpdate(enchere);
        return enchere.getId();
    }

    @PutMapping("/encheres")
    private Enchere updateEnchere(@RequestBody Enchere enchere)
    {
        this.service.saveOrUpdate(enchere);
        return enchere;
    }

    @DeleteMapping("/encheres/{enchereid}")
    private void deleteEnchere(@PathVariable("enchereid") Long enchereid)
    {
        this.service.delete(enchereid);
    }

    // 31/01
    @PostMapping("/encheres/photos")
    private void addPhoto(@RequestBody Enchere_photo[] ep) {
        this.service.savePhoto(ep);
    }

    @GetMapping("/encheres/{idenchere}/photos")
    private List<Enchere_photo> getPhoto(@PathVariable("idenchere") Long enchereid) {
        return this.service.getPhotos(enchereid);
    }

    @PutMapping("/encheres/photos")
    private Enchere_photo updatePhoto(@RequestBody Enchere_photo ep) {
        this.service.updatePhoto(ep);
        return ep;
    }

    @DeleteMapping("/encheres/photos")
    private void deletePhoto(Enchere_photo ep) {
        this.service.deletePhoto(ep);
    }

    @GetMapping("/encheres/historique/utilisateur/{iduser}")
    public List<Historique_enchere> getHistoriqueUtilisateur(@PathVariable("iduser") Long id) {
        List<Historique_enchere> val=this.historique_enchereService.getHistoriqueUtilisateur(id);
        return val;
    }
}
