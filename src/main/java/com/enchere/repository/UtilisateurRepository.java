package com.enchere.repository;

import com.enchere.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository <Utilisateur, Long> {
    @Query(value = "SELECT * FROM utilisateur  WHERE email = ?1 AND mdp = md5(?2)", nativeQuery = true)
    public Utilisateur login(String email, String mdp);
    @Query(value = "SELECT COALESCE(SUM(montant),0) from solde WHERE idutilisateur = ?1 ;", nativeQuery = true)
    public double somme(Long id);
    @Query(value = "SELECT COALESCE(SUM(somme),0) from blocage_compte WHERE idutilisateur = ?1 ;", nativeQuery = true)
    public double sommeBloque(Long id);

    @Query(value = "select count(*) from utilisateur", nativeQuery = true)
    public int nombre();
}
