package com.gatech.buzzdine.entity;

public enum Cuisine {
    American("American", 0), Asian("Asian", 1),
    European("European", 2), Italian("Italian", 3), Mediterranean("Hamburger", 4),
    Indian("Indian", 5), Mexican("Mexican", 6);

    String name;
    int index;
    private Cuisine(String name, int index){
        this.name = name;
        this.index = index;
    }

    public static Cuisine getCuisine(String word){
        for(Cuisine cuisine: Cuisine.values()){
            if (cuisine.getName().equals(word)){
                return cuisine;
            }
        }
        return American;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
