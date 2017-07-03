package com.example.emili.cdf_rosny.data;

import android.widget.ImageView;

/**
 * Created by emili on 05/06/2017.
 */

public class Contact {

    String prenom;
    long numero;
    int imageID;


    public Contact(String prenom, long numero, int imageID){
        this.prenom = prenom;
        this.numero = numero;
        this.imageID= imageID;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public void setNumero(long numero){
        this.numero = numero;
    }

    public void setImageID(int imageID){
        this.imageID = imageID;
    }


    public String getPrenom(){
        return prenom;
    }

    public long getNummero(){
        return numero;
    }

    public int getImageID(){
        return imageID;
    }
}
