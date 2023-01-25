package com.enchere.repository;

import com.enchere.modele.Comission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComissionRepository extends MongoRepository<Comission, String> {

}
