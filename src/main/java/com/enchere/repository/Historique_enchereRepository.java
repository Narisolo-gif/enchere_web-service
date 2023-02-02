package com.enchere.repository;

import com.enchere.modele.Historique_enchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Historique_enchereRepository extends JpaRepository<Historique_enchere, Long> {
    @Query(value = "SELECT * FROM historique_enchere  WHERE idenchere = ?1", nativeQuery = true)
    public List<Historique_enchere> findByIdEnchere(long idEnchere);
    @Query(value = "SELECT * FROM historique_enchere  WHERE idenchere = ?1 ORDER BY date DESC limit 1", nativeQuery = true)
    public Historique_enchere lastHistorique(long idEnchere);
    @Query(value = "SELECT * FROM historique_enchere  WHERE idenchere = ?1 and idutilisateur=?2", nativeQuery = true)
    public List<Historique_enchere> findByIdEnchereUtilisateur(long idEnchere,long idUtilisateur);

    //31
    @Query(value = "SELECT * FROM historique_enchere  WHERE idutilisateur = ?1", nativeQuery = true)
    public List<Historique_enchere> findByIdUtilisateur(long idutilisateur);
}
