package com.enchere.repository;

import com.enchere.modele.Enchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnchereRepository extends JpaRepository <Enchere, Long> {
    @Query(value = "SELECT * FROM enchere  WHERE idutilisateur = ?1", nativeQuery = true)
    public List<Enchere> findByIdUser(long id);
    @Query(value = " SELECT * FROM enchere  WHERE getDateDiffSecond(now(),date_fin)>0 AND id NOT IN (SELECT idenchere FROM vente)", nativeQuery = true)
    public List<Enchere> getEnchereEnCours();
    @Query(value = " SELECT max(prix_mise_enchere) FROM enchere ", nativeQuery = true)
    public double max_prix();
    @Query(value = " SELECT max(id) FROM categorie ", nativeQuery = true)
    public Long max_categorie();
    //idcategorie | prix_mise_enchere |      date_fin       | idutilisateur |  intitule   | p
    @Query(value = " SELECT * FROM enchere  WHERE getDateDiffSecond(now(),date_fin)>0 and prix_mise_enchere between ?1 and ?2 and intitule like %?3% and description like %?3% and idcategorie>=?4 and idcategorie<=?5", nativeQuery = true)
    public List<Enchere> recherche(double prix_min,double prix_max,String mot_cle,Long idcategorie,Long idcategoriemax);

    @Query(value = "select count(*) from enchere", nativeQuery = true)
    public int nombre();
}
