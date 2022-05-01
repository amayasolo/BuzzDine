package com.gatech.buzzdine.controller;

import com.gatech.buzzdine.entity.FilterEnum;
import com.gatech.buzzdine.entity.RestaurantInfo;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.service.RestaurantService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(
            fallbackMethod = "getRecommendFallback"
            ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    }
    )
    public List<RestaurantInfo> getRecommend(@RequestParam String username, @RequestParam String longitude,
                                             @RequestParam String latitude, @RequestParam String filterType){
        try {
            return restaurantService.getRecommend(username, longitude, latitude, FilterEnum.getFilter(Integer.parseInt(filterType)));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @PostMapping("/updateRating")
    @ResponseBody
    @HystrixCommand(
            fallbackMethod = "updateRatingFallback"
            ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    }
    )
    public void updateRating(@RequestParam String username, @RequestParam String restaurantName, @RequestParam Integer rating){
        try {
            restaurantService.updateRating(username, restaurantName, rating);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public String getRecommendFallback(String username, String longitude, String latitude, String filterType, Throwable e){
        return "Service error, please try again " + username;
    }

    public String updateRatingFallback(String username, String restaurantName, Integer rating, Throwable e){
        return "Service error, please try again " + username;
    }
}
