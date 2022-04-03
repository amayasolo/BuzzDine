package com.gatech.buzzdine.controller;

import com.gatech.buzzdine.entity.FilterEnum;
import com.gatech.buzzdine.entity.RestaurantInfo;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<RestaurantInfo> getAll(){
        try {
            return restaurantService.getAll();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/getRecommend")
    @ResponseBody
    public List<RestaurantInfo> getRecommend(@RequestParam String username, @RequestParam String longitude,
                                             @RequestParam String latitude, @RequestParam String filterType){
        try {
            return restaurantService.getRecommend(username, longitude, latitude, FilterEnum.getFilter(Integer.parseInt(filterType)));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
