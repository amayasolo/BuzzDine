package com.gatech.buzzdine.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gatech.buzzdine.entity.Setting;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.storage.service.UserInfoService;
import com.gatech.buzzdine.utils.BuzzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserInfoService userInfoService;

    public boolean register(String username,String email, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setFriends("[]");
        return userInfoService.save(userInfo);
    }

    public boolean login(String username, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", userInfo.getUsername()));
        return dbUserInfo != null && dbUserInfo.getPassword().equals(userInfo.getPassword());
    }

    public Setting getSetting(String username){
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", username));
        Map<String, Integer> userRating = getUserRating(dbUserInfo);
        Map<String, Integer> userPreferences = getUserPreferences(dbUserInfo);
        List<String> ratings = new ArrayList<>(userRating.keySet());
        Collections.sort(ratings, (a, b)->(userRating.get(b) - userRating.get(a)));
        List<String> preferences = new ArrayList<>(userPreferences.keySet());
        Collections.sort(preferences, (a, b)->(userPreferences.get(b) - userPreferences.get(a)));
        Setting res = new Setting();
        res.setUsername(dbUserInfo.getUsername());
        res.setFavourite_dining_place(ratings.get(0));
        res.setLeast_favourite_cuisine(ratings.get(ratings.size() - 1));
        res.setFavourite_cuisine(preferences.get(0));
        res.setLeast_favourite_cuisine(preferences.get(preferences.size() - 1));
        res.setFriends(dbUserInfo.getFriends());
        return res;
    }

    public boolean updateSetting(Setting setting){
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", setting.getUsername()));
        dbUserInfo.setFriends(setting.getFriends());
        dbUserInfo.setUserRating(updateRating(dbUserInfo, setting.getFavourite_dining_place(), setting.getLeast_favourite_dining_place()));
        dbUserInfo.setPreferences(updatePreference(dbUserInfo, setting.getFavourite_cuisine(), setting.getLeast_favourite_cuisine()));
        return userInfoService.save(dbUserInfo);
    }

    private String updatePreference(UserInfo dbUserInfo, String favourite_cuisine, String least_favourite_cuisine) {
        Map<String, Integer> userPreferences = getUserPreferences(dbUserInfo);
        int max = BuzzUtils.getMapMaxValue(userPreferences);
        int min = BuzzUtils.getMapMinValue(userPreferences);
        userPreferences.put(favourite_cuisine, Math.min(max + 1, 10));
        userPreferences.put(least_favourite_cuisine, Math.max(min - 1, -10));
        return BuzzUtils.mapToString(userPreferences);
    }

    private String updateRating(UserInfo dbUserInfo, String favourite_dining_place, String least_favourite_dining_place) {
        Map<String, Integer> userRating = getUserRating(dbUserInfo);
        int max = BuzzUtils.getMapMaxValue(userRating);
        int min = BuzzUtils.getMapMinValue(userRating);
        userRating.put(favourite_dining_place, Math.min(max + 1, 10));
        userRating.put(least_favourite_dining_place, Math.max(min - 1, -10));
        return BuzzUtils.mapToString(userRating);
    }

    public Map<String, Integer> getUserPreferences(UserInfo user) {
        return BuzzUtils.stringToMap(user.getPreferences());
    }

    public Map<String, Integer> getUserRating(UserInfo user) {
        return BuzzUtils.stringToMap(user.getUserRating());
    }
}
