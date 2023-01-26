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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokenadmin_id_seq")
    @SequenceGenerator(name = "tokenadmin_id_seq", sequenceName = "tokenadmin_id_seq",allocationSize = 1)
    private Long id;
    private String valeur;
    @ManyToOne
    @JoinColumn(name = "idadmin")
    private Admin admin;
    private Timestamp expiration;
}
