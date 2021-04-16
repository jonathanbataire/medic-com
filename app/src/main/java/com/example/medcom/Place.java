package com.example.medcom;

public class Place {
    private final String icon;
    private String name;
    private String placeId;
    private String vicinity;

    public Place(String name, String vicinity, String placeId, String icon){
        this.name = name;
        this.vicinity = vicinity;
        this.placeId = placeId;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getIcon() {
        return icon;
    }
}
