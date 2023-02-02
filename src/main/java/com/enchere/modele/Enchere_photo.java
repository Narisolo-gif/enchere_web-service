package com.enchere.modele;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Enchere_photo {
    private int idenchere;
    @Column(columnDefinition = "TEXT")
    private String photo;
}
