package com.enchere.service;

import com.enchere.exception.ResourceNotFoundException;
import com.enchere.modele.Enchere;
import com.enchere.modele.Enchere_photo;
import com.enchere.repository.EnchereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Service
public class EnchereService {

    JdbcTemplate template;
    private final EnchereRepository repository;

    @Autowired
    public EnchereService(EnchereRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public List<Enchere> getEncheres() {
        return this.repository.findAll();
    }

    public Enchere getEnchereById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("enchere n'existe pas avec l'id :" + id));
    }

    public List<Enchere> getEnchereByIdUser(long id) {
        return this.repository.findByIdUser(id)
               /* .orElseThrow(() -> new ResourceNotFoundException("enchere n'existe pas avec l'id :" + id))*/;
    }

    public List<Enchere> getEnchereEnCours() {
        return this.repository.getEnchereEnCours()
               /* .orElseThrow(() -> new ResourceNotFoundException("enchere n'existe pas avec l'id :" + id))*/;
    }

    public List<Enchere_photo> getPhotos(long idenchere) {
        return template.query("select idenchere, photo from enchere_photo where idenchere=" + idenchere, new ResultSetExtractor<List<Enchere_photo>>() {
            @Override
            public List<Enchere_photo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Enchere_photo> list = new ArrayList<Enchere_photo>();
                while (rs.next()) {
                    Enchere_photo ep = new Enchere_photo();
                    ep.setIdenchere(rs.getInt("idenchere"));
                    ep.setPhoto(rs.getBytes("photo"));
                    list.add(ep);
                }
                return list;
            }
        });
    }

    public void savePhoto(Enchere_photo v) {
        template.update("insert into enchere_photo (idenchere, photo) values (?, ?)", v.getIdenchere(), v.getPhoto());
    }

    public void updatePhoto(Enchere_photo v) {
        template.update("update enchere_photo set idenchere=?, photo=?", v.getIdenchere(), v.getPhoto());
    }

    public void deletePhoto(Enchere_photo v) {
        template.update("delete from enchere_photo where idenchere=?, photo=?", v.getIdenchere(), v.getPhoto());
    }

    public void saveOrUpdate(Enchere enchere) {
        this.repository.save(enchere);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }

    public double prixMax(){
        return this.repository.max_prix();
    }

    public Long categorieMax(){
        return this.repository.max_categorie();
    }

    public List<Enchere> recherche(double prix_min,double prix_max,String mot_cle,Long idcategorie,Long idcategoriemax){
        return this.repository.recherche(prix_min, prix_max, mot_cle,idcategorie,idcategoriemax);
    };

    public  int getNombre() {
        return this.repository.nombre();
    }
}
