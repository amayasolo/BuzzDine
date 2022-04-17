package com.gatech.buzzdine.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gatech.buzzdine.entity.FilterEnum;
import com.gatech.buzzdine.entity.RestaurantInfo;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.storage.service.RestaurantInfoService;
import com.gatech.buzzdine.storage.service.UserInfoService;
import com.gatech.buzzdine.utils.BuzzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantInfoService restaurantInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    public List<RestaurantInfo> getAll(){
        return restaurantInfoService.list();
    }

    public List<RestaurantInfo> getRecommend(String username, String longitude, String latitude, FilterEnum filterEnum){
        List<RestaurantInfo> restaurantInfoList = restaurantInfoService.list();
        UserInfo user = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", username));
        List<RestaurantInfo> res = new ArrayList<>();
        List<RestaurantInfo> distanceBasedRes;
        List<RestaurantInfo> contentBasedRes;
        List<RestaurantInfo> collaborateRes;

        switch(filterEnum){
            case DISTANCE:
                return getDistanceBasedRes(restaurantInfoList, longitude, latitude);

            case CONTENT_BASED:
                return getContentBasedRes(user, restaurantInfoList);

            case COLLABORATIVE:
                return getCollaborateRes(user, restaurantInfoList);

            case DISTANCE_CONTENT_BASED:
                contentBasedRes = getContentBasedRes(user, restaurantInfoList);
                distanceBasedRes = getDistanceBasedRes(restaurantInfoList, longitude, latitude);
                res = new ArrayList<>(distanceBasedRes);
                Collections.sort(res, (a, b)->(distanceBasedRes.indexOf(a) + contentBasedRes.indexOf(a) - distanceBasedRes.indexOf(b) - contentBasedRes.indexOf(b)));
                return res;

            case DISTANCE_COLLABORATIVE:
                collaborateRes = getCollaborateRes(user, restaurantInfoList);
                distanceBasedRes = getDistanceBasedRes(restaurantInfoList, longitude, latitude);
                res = new ArrayList<>(distanceBasedRes);
                Collections.sort(res, (a, b)->(distanceBasedRes.indexOf(a) + collaborateRes.indexOf(a) - distanceBasedRes.indexOf(b) - collaborateRes.indexOf(b)));
                return res;

            case DISTANCE_CONTENT_BASED_COLLABORATIVE:
                contentBasedRes = getContentBasedRes(user, restaurantInfoList);
                collaborateRes = getCollaborateRes(user, restaurantInfoList);
                distanceBasedRes = getDistanceBasedRes(restaurantInfoList, longitude, latitude);
                res = new ArrayList<>(distanceBasedRes);
                Collections.sort(res, (a, b)->(distanceBasedRes.indexOf(a) + contentBasedRes.indexOf(a) + collaborateRes.indexOf(a) - distanceBasedRes.indexOf(b) - contentBasedRes.indexOf(b) - collaborateRes.indexOf(b)));
                return res;
        }
        return restaurantInfoService.list();
    }

    private List<RestaurantInfo> getDistanceBasedRes(List<RestaurantInfo> restaurantInfoList, String longitude, String latitude) {
        Collections.sort(restaurantInfoList, (a, b)->(getDistance(a, longitude, latitude) > getDistance(b, longitude, latitude) ? 1 : -1));
        return restaurantInfoList;
    }

    private List<RestaurantInfo>  getCollaborateRes(UserInfo user, List<RestaurantInfo> restaurantInfoList) {
        Map<RestaurantInfo, Double> scoreMap = new HashMap<>();
        Map<String, Integer> userPreferences = userService.getUserPreferences(user);
        List<UserInfo> allUser = userInfoService.list();
        Set<String> friends = new HashSet<>(getFriends(user));
        for(UserInfo other : allUser){
            if(other.getUsername().equals(user.getUsername())) continue;
            Map<String, Integer> otherPreferences = userService.getUserPreferences(other);
            int similarity = 0;
            if(!otherPreferences.isEmpty()){
                for(String p : userPreferences.keySet()){
                    similarity += Math.abs(userPreferences.get(p) - otherPreferences.getOrDefault(p, 0)) <= 1 ? 1 : 0;
                }
            }
            List<RestaurantInfo> favorites = getFavorite(other, restaurantInfoList);
            if(friends.contains(user.getUsername())) similarity *= 1.2;
            for(RestaurantInfo f : favorites){
                scoreMap.put(f, scoreMap.getOrDefault(f, 0d) + similarity);
            }
        }
        List<RestaurantInfo> res = new ArrayList<>(restaurantInfoList);
        Collections.sort(res,(a, b)->(scoreMap.getOrDefault(a, 0d) > scoreMap.getOrDefault(b, 0d) ? -1: 1) );
        return res;
    }

    private List<String> getFriends(UserInfo user) {
        String friends = user.getFriends();
        return (null == friends || friends.isEmpty()) ? new ArrayList<>() : Arrays.asList(user.getFriends().split(","));
    }

    private List<RestaurantInfo> getFavorite(UserInfo user, List<RestaurantInfo> restaurantInfoLis) {
        List<RestaurantInfo> res = new ArrayList<>();
        Map<String, Integer> ratings = userService.getUserRating(user);
        if(ratings.isEmpty()) return res;
        int max = Integer.MIN_VALUE;
        for(String r : ratings.keySet()){
            max = Math.max(max, ratings.get(r));
        }
        for(RestaurantInfo r : restaurantInfoLis){
            if(ratings.getOrDefault(r.getName(), 0) == max) res.add(r);
        }
        return  res;
    }

    private List<RestaurantInfo> getContentBasedRes(UserInfo user, List<RestaurantInfo> restaurantInfoList) {
        Map<RestaurantInfo, Double> scoreMap = new HashMap<>();
        Map<String, Integer> userRating = userService.getUserRating(user);
        Map<String, Integer> userPreferences = userService.getUserPreferences(user);
        for(RestaurantInfo restaurant : restaurantInfoList){
            Set<String> features = new HashSet<>(Arrays.asList(restaurant.getFeatures().split(",")));
            Double tmp = 0d;
            for(String f : features){
                tmp += userPreferences.getOrDefault(f, 0);
            }
            Double score = 0.5 * userRating.getOrDefault(restaurant.getId(), 0) + 0.5 * tmp / userPreferences.size();
            scoreMap.put(restaurant, score);
        }

        List<RestaurantInfo> res = new ArrayList<>(scoreMap.keySet());
        Collections.sort(res, (a, b)->(scoreMap.get(a) > scoreMap.get(b) ? -1: 1) );
        return res;
    }

    private double getDistance(RestaurantInfo re, String lo, String la){
        double dis1 = Double.valueOf(re.getLatitude()) - Double.valueOf(la);
        double dis2 = Double.valueOf(re.getLongitude()) - Double.valueOf(lo);
        return dis1 * dis1 + dis2 * dis2;
    }

    public boolean updateRating(String username, String restaurantName, int rating){
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", username));
        Map<String, Integer> ratings = userService.getUserRating(dbUserInfo);
        if (ratings == null){
            ratings = new HashMap<>();
        }
        ratings.put(restaurantName, rating);
        dbUserInfo.setUserRating(BuzzUtils.mapToString(ratings));
        return userInfoService.saveOrUpdate(dbUserInfo);
    }
}
