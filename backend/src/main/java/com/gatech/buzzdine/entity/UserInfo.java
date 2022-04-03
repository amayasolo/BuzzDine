package com.gatech.buzzdine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserInfo {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String userVector;
    private String userRating;
}
