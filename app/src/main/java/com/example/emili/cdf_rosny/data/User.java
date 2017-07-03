package com.example.emili.cdf_rosny.data;

/**
 * Created by emili on 23/05/2017.
 */

public class User {

    private String nom;
    private String prenom;
    private String email;


    public User(){
        //default constructeur for call the DataSnapShots
    }

    public User(String nom, String prenom, String email){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom(){
        return nom;
    }
    public  void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }
    public  void setPrenomom(String nom){
        this.prenom = prenom;
    }


    public String getEmail(){
        return email;
    }
    public  void setEmail(String email){
        this.email = email;
    }

}