package com.gatech.buzzdine.entity;

public enum FilterEnum {
    DISTANCE("distance", 0), CONTENT_BASED("content-based", 1), COLLABORATIVE("collaborative", 2),
    DISTANCE_CONTENT_BASED("distance and content-based", 3), DISTANCE_COLLABORATIVE("distance and collaborative", 4),
    DISTANCE_CONTENT_BASED_COLLABORATIVE("combination of location, content-based and collaborative filtering", 5);

    private String type;
    private int index;

    FilterEnum(String type, int index){
        this.type = type;
        this.index = index;
    }

    public static FilterEnum getFilter(int index){
        for(FilterEnum filterEnum: FilterEnum.values()){
            if (filterEnum.getIndex() == index){
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
