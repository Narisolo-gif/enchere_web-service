package com.enchere.controller;

import com.enchere.modele.Enchere;
import com.enchere.modele.Historique_enchere;
import com.enchere.modele.Token;
import com.enchere.modele.Utilisateur;
import com.enchere.service.Blocage_compteService;
import com.enchere.service.EnchereService;
import com.enchere.service.Historique_enchereService;
import com.enchere.service.UtilisateurService;
import com.enchere.utils.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UtilisateurController {
    private final UtilisateurService service;
    private final Historique_enchereService hs;
    private final EnchereService es;
    private final UtilisateurService utilisateur_service;
    private final Blocage_compteService blocage_compteService;
    @Autowired
    public UtilisateurController(UtilisateurService service, Historique_enchereService hs, EnchereService es,UtilisateurService utilisateur_service,Blocage_compteService blocage_compteService) {

        this.service = service;
        this.hs=hs;
        this.es=es;
        this.utilisateur_service=utilisateur_service;
        this.blocage_compteService=blocage_compteService;
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getUtilisateur() {
        return this.service.getUtilisateur();
    }

    @GetMapping("/utilisateurs/{utilisateurid}")
    private Utilisateur getUtilisateur(@PathVariable("utilisateurid") Long utilisateurid)
    {
        return this.service.getUtilisateurById(utilisateurid);
    }

    @PostMapping("/utilisateurs")
    private Long saveUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        this.service.saveOrUpdate(utilisateur);
        return utilisateur.getId();
    }

    @PutMapping("/utilisateurs")
    private Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        this.service.saveOrUpdate(utilisateur);
        return utilisateur;
    }

    @DeleteMapping("/utilisateurs/{utilisateurid}")
    private void deleteUtilisateur(@PathVariable("utilisateurid") Long utilisateurid)
    {
        this.service.delete(utilisateurid);
    }

    /*@PostMapping("/utilisateurs/login")
    public Token logUtilisateur(@RequestParam("email") String email, @RequestParam("mdp") String mdp){
        return this.service.login(email, mdp);
    }*/

    @PostMapping("/encherir/{idutilisateur}/{idenchere}/{somme}")
    public void encherir(@PathVariable("idutilisateur")Long idutilisateur, @PathVariable("idenchere")Long idenchere,@PathVariable("somme")double somme)throws Exception{
        Enchere enchere=es.getEnchereById(idenchere);
        if(enchere.getStatut()==0 && this.service.somme(idutilisateur)-this.service.sommeBloque(idutilisateur)>somme ){
            // somme>this.hs.lastHistorique(idenchere).getPrix()&&idutilisateur!=this.hs.lastHistorique(idenchere).getUtilisateur().getId()
            Historique_enchere historique=new Historique_enchere();
            historique.setEnchere(enchere);
            historique.setPrix(somme);
            historique.setDate(new Timestamp(System.currentTimeMillis()));
            historique.setUtilisateur(utilisateur_service.getUtilisateurById(idutilisateur));
            
            if(this.hs.lastHistorique(idenchere)==null ){
                
                hs.saveOrUpdate(historique);

                this.blocage_compteService.deleteBlocage(idenchere,idutilisateur);
            }else {
                if(somme>this.hs.lastHistorique(idenchere).getPrix()&&idutilisateur!=this.hs.lastHistorique(idenchere).getUtilisateur().getId()){
                    hs.saveOrUpdate(historique);

                    this.blocage_compteService.deleteBlocage(idenchere,idutilisateur);
                }
            }

        }else{
            throw new Exception("Operation invalide");
        }
    }
    @DeleteMapping("/historique/{idutilisateur}/{idenchere}")
    public void supprimer(@PathVariable("idutilisateur")Long idutilisateur, @PathVariable("idenchere")Long idenchere){
        this.blocage_compteService.deleteBlocage(idenchere,idutilisateur);
    }
    @GetMapping("/utilisateur/{id}/somme")
    public double somme(@PathVariable("id")Long id){
        return this.service.somme(id);
    }
    @GetMapping("/historique/{idenchere}")
    private Historique_enchere lastHistorique(@PathVariable("idenchere") Long idenchere)
    {
        return this.hs.lastHistorique(idenchere);
    }

    @GetMapping("/utilisateurs/count")
    private int getNombre()
    {
        return this.service.getNombre();
    }

    // 31/01
    @PostMapping("/utilisateurs/login")
    public Token logUtilisateur(@RequestBody Login l) {
        return this.service.login(l.getEmail(), l.getMdp());
    }
    @GetMapping("/encheres/{idenchere}/lasthistorique")
    public Historique_enchere lastHitorique(@PathVariable("idenchere")Long idenchere){
        return this.hs.lastHistorique(idenchere);
    }
}
