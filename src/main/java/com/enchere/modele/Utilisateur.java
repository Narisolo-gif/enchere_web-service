package com.enchere.modele;

import lombok.*;
import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String CIN;
    private Date date_naissance;
    private String email;
    private String mdp;

    public void setMdp(String mdp) {
        this.mdp = getMd5Hash(mdp);
    }

    public static String getMd5Hash(String input)
    {
        try
        {
//static getInstance() method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
//calculating message digest of an input that return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
//converting byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
//converting message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
//for specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}
