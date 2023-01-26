package com.enchere.repository;

import com.enchere.modele.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository <Categorie, Long> {
    @Query(value = "select nextval('s_categorie');", nativeQuery = true)
    public Long getId();
    /*@Query(value = "INSERT INTO categorie values (?1,?2);", nativeQuery = true)
    public void saveSafe(Long id,String libelle);*/

}
