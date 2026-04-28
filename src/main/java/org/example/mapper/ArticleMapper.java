package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.entity.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article (title, content, cover_img,state,category_id,create_user,create_time,update_time)" +
            " values (#{title}, #{content}, #{coverImg},#{state},#{categoryId},#{createUser},#{createTime}," +
            "#{updateTime})")
    void addArticle(Article article);

    List<Article> list(@Param("categoryId") Integer categoryId, @Param("state") String state,
                       @Param("createUser") int createUser);

    @Select("select * from article where id = #{id}")
    Article detail(int id);
    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}," +

            "update_time = #{updateTime} where id = #{id}")
    void update(Article article);
    @Delete("delete from article where id = #{id}")
    void delete(int id);
}
