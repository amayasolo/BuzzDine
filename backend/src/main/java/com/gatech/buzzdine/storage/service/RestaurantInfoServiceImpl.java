package com.gatech.buzzdine.storage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gatech.buzzdine.entity.RestaurantInfo;
import com.gatech.buzzdine.storage.mapper.RestaurantMapper;
import org.springframework.stereotype.Service;

@Service
public class RestaurantInfoServiceImpl extends ServiceImpl<RestaurantMapper, RestaurantInfo> implements RestaurantInfoService{
}