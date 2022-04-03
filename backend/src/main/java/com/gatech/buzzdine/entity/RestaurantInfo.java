package com.gatech.buzzdine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("restaurant")
public class RestaurantInfo {
    private int id;
    private String name;
    private long longitude;
    private long latitude;
}
