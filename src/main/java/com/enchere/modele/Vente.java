package com.enchere.modele;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vente {
    private Enchere enchere;
    private Utilisateur utilisateur;
    private double prix;
}
