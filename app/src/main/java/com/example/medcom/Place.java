package com.example.medcom;

public class Place {
    private static String place_name;

    public String getPlaceName(){
        return place_name;
    }
    public void setPlace_name(String name){
        place_name = name;
    }
    public void reset(){
        place_name = null;
    }
}
