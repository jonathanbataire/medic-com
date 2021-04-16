package com.example.medcom;

public class Place {
    private String name;
    private String placeId;
    private String vicinity;

    public Place(String name, String vicinity, String placeId){
        this.name = name;
        this.vicinity = vicinity;
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
