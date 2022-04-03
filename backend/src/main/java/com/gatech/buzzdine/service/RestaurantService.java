package com.gatech.buzzdine.service;

import com.gatech.buzzdine.entity.RestaurantInfo;
import com.gatech.buzzdine.storage.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantInfoService restaurantInfoService;

    public List<RestaurantInfo> getAll(){
        return restaurantInfoService.list();
    }

    public List<RestaurantInfo> getRecommend(String username, String longitude, String latitude){
        return restaurantInfoService.list();
    }
}
