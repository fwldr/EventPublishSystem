package org.example.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.anno.ArticleState;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
public class Article {

    private int id;
    @NotEmpty
    @Pattern(message = "标题格式不正确", regexp = "^\\S{1,20}$")
    private String title;
    @NotEmpty
    private String content;
    @URL
    @NotEmpty
    private String coverImg;
    @ArticleState
    private String state;
    @NotNull
    private int categoryId;
    private int createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
