package com.gatech.buzzdine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    private String username;
    private String favourite_dining_place;
    private String least_favourite_dining_place;
    private String favourite_cuisine;
    private String least_favourite_cuisine;
    // "username1, username2"
    private String friends;
}
