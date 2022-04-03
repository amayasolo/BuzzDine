package com.gatech.buzzdine.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.storage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserInfoService userInfoService;

    public boolean register(UserInfo userInfo){
        return userInfoService.save(userInfo);
    }

    public boolean login(UserInfo userInfo){
        UserInfo dbUserInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("username", userInfo.getUsername()));
        return dbUserInfo != null && dbUserInfo.getPassword().equals(userInfo.getPassword());
    }
}
