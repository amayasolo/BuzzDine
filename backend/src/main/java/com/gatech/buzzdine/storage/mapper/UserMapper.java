package com.gatech.buzzdine.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gatech.buzzdine.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserInfo> {
}