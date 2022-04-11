package com.gatech.buzzdine.storage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gatech.buzzdine.entity.RestaurantInfo;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.storage.mapper.RestaurantMapper;
import com.gatech.buzzdine.storage.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserInfoService{
}