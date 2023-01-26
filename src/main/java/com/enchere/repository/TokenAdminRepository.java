package com.enchere.repository;

import com.enchere.modele.TokenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenAdminRepository extends JpaRepository <TokenAdmin, Long> {
    @Query(value = "SELECT * FROM tokenAdmin WHERE idadmin=?1 ORDER BY id LIMIT 1", nativeQuery = true)
    public TokenAdmin getByIdAdmin(long id);
    @Query(value = "DELETE from tokenadmin", nativeQuery = true)
    public void deleteAll();
}
