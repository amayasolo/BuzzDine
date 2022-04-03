package com.gatech.buzzdine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserInfo {
    private int id;
    private String username;
    private String email;
    private String password;
    private String userVector;
    // {restaurantId1: 5, restaurantId2: 4, restaurantId3: 5}
    private String userRating;
    // ["username1", "username2"]
    private String friends;
}
