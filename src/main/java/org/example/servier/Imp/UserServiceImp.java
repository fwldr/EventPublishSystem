package org.example.servier.Imp;

import jakarta.annotation.Resource;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.servier.UserService;
import org.example.util.Md5Util;
import org.example.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public int registerUser(String username, String password) {

        String psd = Md5Util.getMD5String(password);

        return userMapper.registerUser(username, psd);

    }

    @Override
    public void updateUser(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now);
         userMapper.updateUser(user);
    }

    @Override
    public void updateAvatar(String userPic ){
        Map<String, Object> claims = ThreadLocalUtil.get();
        Object id = claims.get("id");
        userMapper.updateAvatar(userPic, id);

    }

    @Override
    public void updatePwd(String password) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        userMapper.updatePwd(password, id );

    }
}
