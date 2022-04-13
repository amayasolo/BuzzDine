package com.gatech.buzzdine.controller;

import com.gatech.buzzdine.entity.Setting;
import com.gatech.buzzdine.entity.UserInfo;
import com.gatech.buzzdine.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public boolean register(@RequestParam String username, @RequestParam String email,
                              @RequestParam String password){
        try {
            return userService.register(username, email, password);
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @GetMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password){
        try {
            return userService.login(username, password);
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @PostMapping("/updateSetting")
    public boolean updateSetting(@RequestBody Setting setting){
        try {
            return userService.updateSetting(setting);
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @GetMapping("/getSetting")
    @ResponseBody
    public Setting getSetting(@RequestParam String username){
        try {
            return userService.getSetting(username);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new Setting();
        }
    }
}