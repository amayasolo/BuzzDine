package com.gatech.buzzdine.entity;

public enum FilterType {
    DISTANCE("Distance", 0), CONTENT_BASED("Content", 1), COLLABORATIVE("Friends", 2),
    DISTANCE_CONTENT_BASED("Distance and Content", 3), DISTANCE_COLLABORATIVE("Distance and Friends", 4),
    DISTANCE_CONTENT_BASED_COLLABORATIVE("Distance and Content and Friends", 5);

    private String type;
    private int index;

    FilterType(String type, int index){
        this.type = type;
        this.index = index;
    }

    public static FilterType getFilter(String word){
        for(FilterType filterEnum: FilterType.values()){
            if (filterEnum.getType().equals(word)){
                return filterEnum;
            }
        }
        return DISTANCE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
