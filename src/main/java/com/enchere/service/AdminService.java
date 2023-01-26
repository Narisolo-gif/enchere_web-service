package com.enchere.service;

import com.enchere.modele.*;
import com.enchere.repository.*;
import com.enchere.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
public class AdminService {
    private final AdminRepository repository;
    private final TokenAdminRepository tokenAdminRepository;

    @Autowired
    public AdminService(AdminRepository repository,
                        TokenAdminRepository tokenAdminRepository) {
        this.repository = repository;
        this.tokenAdminRepository = tokenAdminRepository;
    }

    public TokenAdmin login(String email, String mdp){
        Admin u = this.repository.login(email, mdp);
        TokenAdmin t = null;
        if (u != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 30);
            Date date = calendar.getTime();
            t = new TokenAdmin();
            t.setAdmin(u);
            t.setValeur(new TokenGenerator().generateToken());
            t.setExpiration(new Timestamp(date.getTime()));
            tokenAdminRepository.save(t);
        }
        return t;
    }
}
