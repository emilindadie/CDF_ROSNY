package com.example.emili.cdf_rosny.data;

/**
 * Created by emili on 03/06/2017.
 */

public class FeedsModel {

    String id, type, sectionName;

    public FeedsModel(String id, String type, String sectionName){

        this.id = id;
        this.type = type;
        this.sectionName = sectionName;
    }


    public String getId(){
        return id;
    }

    public String getType(){

        return  type;
    }
    public String getSectionName(){

        return  sectionName;
    }
}
