package com.enchere.repository;

import com.enchere.modele.Solde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldeRepository extends JpaRepository <Solde, Long> {

}
