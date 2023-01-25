package com.enchere.repository;

import com.enchere.modele.Demande_rechargement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Demande_rechargementRepository extends JpaRepository <Demande_rechargement, Long> {
    @Query(value = "update demande_rechargement set statut=1 where id=?1", nativeQuery = true)
    public void valider(long id);

    @Query(value = "select * from demande_rechargement where statut=?1", nativeQuery = true)
    public List<Demande_rechargement> getByStatut(long statut);
}
