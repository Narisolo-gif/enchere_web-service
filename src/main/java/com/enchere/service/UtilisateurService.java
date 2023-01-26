package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Token;
import com.enchere.modele.Utilisateur;
import com.enchere.repository.TokenRepository;
import com.enchere.repository.UtilisateurRepository;
import com.enchere.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository repository,
                              TokenRepository tokenRepository) {
        this.repository = repository;
        this.tokenRepository = tokenRepository;
    }

    public List<Utilisateur> getUtilisateur() {
        return this.repository.findAll();
    }

    public Utilisateur getUtilisateurById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("utilisateur n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Utilisateur utilisateur) {
        this.repository.save(utilisateur);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }



    public double somme(long id) {
        return this.repository.somme(id);
    }

    public double sommeBloque(long id) {

        return this.repository.sommeBloque(id);
    }

    public Token login(String email, String mdp){
        Utilisateur u = this.repository.login(email, mdp);
        Token t = null;
        if (u != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 30);
            Date date = calendar.getTime();
            t = new Token();
            t.setUtilisateur(u);
            t.setValeur(new TokenGenerator().generateToken());
            t.setExpiration(new Timestamp(date.getTime()));
            tokenRepository.save(t);
        }
        return t;
    }

    public  int getNombre() {
        return this.repository.nombre();
    }
}
