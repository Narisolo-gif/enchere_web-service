package com.enchere.modele;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "enchere")
public class Enchere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    private String description;
    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;
    private double prix_mise_enchere;
    private Timestamp date_fin;
    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;
    private String intitule;
    @Column(columnDefinition = "TEXT")
    private String photo_couverture;
    @Transient
    private int statut;
    
    public int getStatut(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long diff = date_fin.getTime() - now.getTime();
        System.out.println(diff);
        if(diff<=0){
            this.statut=1;
        }
        return statut;
    }
}
