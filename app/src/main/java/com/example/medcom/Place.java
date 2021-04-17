package com.example.medcom;

public class Place {
    private final String icon;
    private String name;
    private String place_id;
    private String vicinity;

    public Place(String name, String vicinity, String place_id, String icon){
        this.name = name;
        this.vicinity = vicinity;
        this.place_id = place_id;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getIcon() {
        return icon;
    }
}
