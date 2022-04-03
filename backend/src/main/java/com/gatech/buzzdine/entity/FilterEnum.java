package com.gatech.buzzdine.entity;

public enum FilterEnum {
    PICKY("picky", 1), DISTANCE("distance", 2), INTEGRATE("integrate", 3);

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
        return null;
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
