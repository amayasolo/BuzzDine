package com.gatech.buzzdine.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gatech.buzzdine.entity.RestaurantInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantMapper extends BaseMapper<RestaurantInfo> {
}