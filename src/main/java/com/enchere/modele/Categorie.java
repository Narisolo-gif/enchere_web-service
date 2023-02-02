package com.enchere.modele;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "categorie")
public class Categorie {
    @Id
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_categorie")
    @SequenceGenerator(name = "s_categorie", sequenceName = "s_categorie",allocationSize = 1)*/

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
}
