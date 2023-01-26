package com.enchere.modele;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tokenadmin")
public class TokenAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_token_admin")
    @SequenceGenerator(name = "s_token_admin", sequenceName = "s_token_admin",allocationSize = 1)
    private Long id;
    private String valeur;
    @ManyToOne
    @JoinColumn(name = "idadmin")
    private Admin admin;
    private Timestamp expiration;
}
