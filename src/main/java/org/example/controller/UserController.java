package org.example.controller;


import ch.qos.logback.core.util.MD5Util;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.example.entity.Result;
import org.example.entity.User;
import org.example.servier.UserService;
import org.example.util.JwtUtil;
import org.example.util.Md5Util;
import org.example.util.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp="^\\S{5,16}$") @RequestParam String username ,
                           @Pattern(regexp="^\\S{5,16}$") @RequestParam String password){
        User userByName = userService.getUserByName(username);
        if(userByName == null){
            userService.registerUser(username,password);
            String message = "注册成功";
            return Result.success(message);
        }else {
            String message = "用户名已存在";
            return Result.error(message);
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp="^\\S{5,16}$") @RequestParam String username ,
                        @Pattern(regexp="^\\S{5,16}$") @RequestParam String password){
        User userByName = userService.getUserByName(username);
        if(userByName == null){
            String message = "用户名不存在";
            return Result.error(message);
        }else {
            if(userByName.getPassword().equals(Md5Util.getMD5String(password))){
                String message = "登录成功";
                Map<String ,Object> claims = new HashMap<>();
                claims.put("id", userByName.getId());
                claims.put("username", userByName.getUsername());
                String token = JwtUtil.genToken(claims);
                stringRedisTemplate.opsForValue().set(token, token,1, TimeUnit.HOURS);
                return Result.success(message, token);
            }
            else {
                String message = "用户名或密码错误";
                return Result.error(message);
            }
        }
    }


    @GetMapping("/userInfo")
    public Result userInfo(){
         Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        String username = (String) stringObjectMap.get("username");
        User userByName = userService.getUserByName(username);
        String message = "获取用户信息成功";
        return Result.success(message, userByName);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.updateUser(user);
        String message = "更新用户信息成功";
        return Result.success(message);

    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String userPic){
        userService.updateAvatar(userPic);
        String message = "更新头像成功";
        return Result.success(message);
    }


    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token){
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        String rePassword = params.get("rePassword");

        if(!StringUtils.hasLength(oldPassword)||!StringUtils.hasLength(newPassword)||!StringUtils.hasLength(rePassword)){
            return Result.error("参数不能为空");
        }
        if(!newPassword.equals(rePassword)){
            return Result.error("两次密码不一致");
        }
        Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        String username = (String) stringObjectMap.get("username");
        User userByName = userService.getUserByName(username);
        if(!userByName.getPassword().equals(Md5Util.getMD5String(oldPassword))){
            return Result.error("旧密码错误");
        }
        userService.updatePwd(Md5Util.getMD5String(newPassword));
        String message = "更新密码成功";
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return Result.success(message);
    }

}
