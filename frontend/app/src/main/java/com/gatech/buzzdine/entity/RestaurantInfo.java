package com.gatech.buzzdine.entity;

public class RestaurantInfo {
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    // Cuisine Feature: American Barbecue Chinese French Hamburger Indian Italian Japanese Mexican Pizza Seafood Steak Sushi Thai}
    //["American", "Chinese"]
    private String features;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
