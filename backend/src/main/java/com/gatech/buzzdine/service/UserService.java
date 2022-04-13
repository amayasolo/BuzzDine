package com.gatech.buzzdine.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gatech.buzzdine.entity.Setting;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.storage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        //TODO 初始值
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
        //TODO
        return new Setting();
    }

    //TODO
    public boolean updateSetting(Setting setting){
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", setting.getUsername()));
//        UserInfo otherDbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", otherUsername));
//        List<String> friendList = JSONArray.parseArray(dbUserInfo.getFriends(), String.class);
//        List<String> otherFriendList = JSONArray.parseArray(otherDbUserInfo.getFriends(), String.class);
//        friendList.add(otherUsername);
//        otherFriendList.add(myUsername);
//        dbUserInfo.setFriends(JSONArray.toJSONString(friendList));
//        otherDbUserInfo.setFriends(JSONArray.toJSONString(otherFriendList));
//        List<UserInfo> saveList = new ArrayList<>();
//        saveList.add(dbUserInfo);
//        saveList.add(otherDbUserInfo);
//        return userInfoService.saveOrUpdateBatch(saveList);
        return true;
    }
}
