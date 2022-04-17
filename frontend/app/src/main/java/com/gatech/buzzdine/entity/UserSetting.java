package com.gatech.buzzdine.entity;

public class UserSetting {
    private String username;
    private String favourite_dining_place;
    private String least_favourite_dining_place;
    private String favourite_cuisine;
    private String least_favourite_cuisine;
    // "username1, username2"
    private String friends;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFavourite_dining_place() {
        return favourite_dining_place;
    }

    public void setFavourite_dining_place(String favourite_dining_place) {
        this.favourite_dining_place = favourite_dining_place;
    }

    public String getLeast_favourite_dining_place() {
        return least_favourite_dining_place;
    }

    public void setLeast_favourite_dining_place(String least_favourite_dining_place) {
        this.least_favourite_dining_place = least_favourite_dining_place;
    }

    public String getFavourite_cuisine() {
        return favourite_cuisine;
    }

    public void setFavourite_cuisine(String favourite_cuisine) {
        this.favourite_cuisine = favourite_cuisine;
    }

    public String getLeast_favourite_cuisine() {
        return least_favourite_cuisine;
    }

    public void setLeast_favourite_cuisine(String least_favourite_cuisine) {
        this.least_favourite_cuisine = least_favourite_cuisine;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }
}

