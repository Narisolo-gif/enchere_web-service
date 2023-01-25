package com.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enchere.modele.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(value = "SELECT * FROM admin  WHERE email = ?1 AND mdp = md5(?2)", nativeQuery = true)
    public Admin login(String email, String mdp);
}
