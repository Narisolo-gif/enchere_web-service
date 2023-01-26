package com.enchere.repository;

import com.enchere.modele.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository <Token, Long> {
    @Query(value = "SELECT * FROM token WHERE idutilisateur=?1 ORDER BY id LIMIT 1", nativeQuery = true)
    public Token getByIdUtilisateur(long id);

}
