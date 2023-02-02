package com.enchere.modele;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("comission")
public class Comission {
    @Id
    private String id;
    private int pourcentage;
}
