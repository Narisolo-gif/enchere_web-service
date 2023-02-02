package com.enchere.service;

import com.enchere.modele.Comission;
import com.enchere.modele.Enchere;
import com.enchere.modele.Enchere_photo;
import com.enchere.modele.Vente;
import com.enchere.repository.ComissionRepository;
import com.enchere.repository.EnchereRepository;
import com.enchere.repository.TokenRepository;
import com.enchere.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VenteService {
    JdbcTemplate template;
    private final EnchereRepository erep;
    private final UtilisateurRepository urep;
    private final TokenRepository trep;
    private final ComissionRepository comissionRepository;

    EnchereService eserv;
    UtilisateurService userv;
    ComissionService comissionService;

    @Autowired
    public VenteService(
            EnchereRepository erep,
            UtilisateurRepository urep,
            TokenRepository trep,
            ComissionRepository comissionRepository
        ) {
        this.erep = erep;
        this.urep = urep;
        this.trep = trep;
        this.comissionRepository = comissionRepository;
        this.comissionService = new ComissionService(comissionRepository);
        eserv = new EnchereService(erep);
        userv = new UtilisateurService(urep, trep);
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public List<Vente> getVentes() {
        return template.query("select idenchere, idutilisateur, prix from vente", new ResultSetExtractor<List<Vente>>() {
            @Override
            public List<Vente> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Vente> list = new ArrayList<Vente>();
                while (rs.next()) {
                    Vente ep = new Vente();
                    ep.setEnchere(eserv.getEnchereById(rs.getLong("idenchere")));
                    ep.setUtilisateur(userv.getUtilisateurById(rs.getLong("idutilisateur")));
                    ep.setPrix(rs.getDouble("prix"));
                    list.add(ep);
                }
                return list;
            }
        });
    }

    public List<Vente> getVentesByIdUtilisateur(long idutilisateur) {
        return template.query("select idenchere, idutilisateur, prix from vente where idutilisateur=" + idutilisateur, new ResultSetExtractor<List<Vente>>() {
            @Override
            public List<Vente> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Vente> list = new ArrayList<Vente>();
                while (rs.next()) {
                    Vente ep = new Vente();
                    ep.setEnchere(eserv.getEnchereById(rs.getLong("idenchere")));
                    ep.setUtilisateur(userv.getUtilisateurById(rs.getLong("idutilisateur")));
                    ep.setPrix(rs.getDouble("prix"));
                    list.add(ep);
                }
                return list;
            }
        });
    }

    public double getBenefice() {
        double rep = 0;
        List<Vente> ventes = getVentes();
        List<Comission> comissions = comissionService.getComissions();
        for (int i = 0; i < ventes.size(); i++) {
            rep += ventes.get(i).getPrix() * (comissions.get(0).getPourcentage()) / 100;
        }
        return rep;
    }

    public void save(Vente v) {
        template.update("insert into vente (idenchere, idutilisateur, prix) values (?, ?, ?)", v.getEnchere().getId(), v.getUtilisateur().getId(), v.getPrix());
    }

    public void update(Vente v) {
        template.update("update vente set idenchere=?, idutilisateur=?, prix=?  where idenchere=? and idutilisateur=?", v.getEnchere().getId(), v.getUtilisateur().getId(), v.getPrix(), v.getEnchere().getId(), v.getUtilisateur().getId());
    }
}
