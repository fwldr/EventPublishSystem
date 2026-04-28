package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.User;

@Mapper
public interface UserMapper {

    @Update("update user set password = #{password}, update_time = now() where id = #{id}")
    void updatePwd(String password, int id);



    @Select("select * from user where username = #{username}")
    User getUserByName(String username);
    @Insert("insert into user (username, password, create_time, update_time) "+
            "values (#{username}, #{password}, now(),now())")
    int registerUser(String username , String password);
    @Update("update user set nickname = #{nickname},email = #{email}, update_time = #{updateTime} where id = #{id}")
    void updateUser(User user);
    @Update("update user set user_pic = #{userPic}, update_time = now() where id = #{id}")
    void updateAvatar(String userPic, Object id);

}
