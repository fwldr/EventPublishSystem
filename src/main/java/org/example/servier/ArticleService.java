package org.example.servier;

import org.example.entity.Article;
import org.example.entity.PageBean;

public interface ArticleService {
    void addArticle(Article article);

    PageBean<Article> list(int pageNum, int pageSize, Integer categoryId, String state);

    Article detail(int id);

    void update(Article article);

    void delete(int id);

}
