package org.example.servier;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.entity.User;

public interface UserService {

    User getUserByName(String username);

    int registerUser(String username , String password);


    void updateUser(User user);

    void updateAvatar(String avatar);

    void updatePwd(String password);
}
