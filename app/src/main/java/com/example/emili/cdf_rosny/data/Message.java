package com.example.emili.cdf_rosny.data;

import android.view.MenuItem;

/**
 * Created by emili on 08/06/2017.
 */

public class Message {
    private String message;
    private String nom;
    private String photoUrl;

    public Message(){

    }
    public Message(String message, String nom, String photoUrl){

    this.message = message;
    this.nom = nom;
    this.photoUrl = photoUrl;

    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){

        this.message = message;
    }

    public String getNom(){
        return nom;
    }
    public void setNom(String nom){

        this.nom = nom;
    }

    public String getPhotoUrl(){
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl){

        this.photoUrl = photoUrl;
    }




}
