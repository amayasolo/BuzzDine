package com.gatech.buzzdine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("restaurant")
public class RestaurantInfo {
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    // Cuisine Feature: American Barbecue Chinese French Hamburger Indian Italian Japanese Mexican Pizza Seafood Steak Sushi Thai}
    //["American", "Chinese"]
    private String features;
}
