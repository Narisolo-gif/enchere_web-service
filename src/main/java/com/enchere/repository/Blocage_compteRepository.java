package com.enchere.repository;

import com.enchere.modele.Blocage_compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Blocage_compteRepository extends JpaRepository <Blocage_compte, Long> {
    @Query(value = " SELECT id FROM blocage_compte  WHERE idenchere=?1 and idutilisateur!=?2", nativeQuery = true)
    public List<Long> deleteBlocage(Long idEnchere, Long idUtilisateur);
}
