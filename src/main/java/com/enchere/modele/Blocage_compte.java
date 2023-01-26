package com.enchere.modele;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "blocage_compte")
public class Blocage_compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utitisateur;
    @ManyToOne
    @JoinColumn(name = "idenchere")
    private Enchere enchere;
    private double somme;
}
