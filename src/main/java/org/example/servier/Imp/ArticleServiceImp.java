package org.example.servier.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Article;
import org.example.entity.PageBean;
import org.example.mapper.ArticleMapper;
import org.example.servier.ArticleService;
import org.example.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ArticleServiceImp implements ArticleService {
    @Resource
    ArticleMapper articleMapper;


    @Override
    public void addArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> claims =ThreadLocalUtil.get();
        article.setCreateUser((int) claims.get("id"));
        articleMapper.addArticle(article);

    }

    @Override
    public PageBean<Article> list(int pageNum, int pageSize, Integer categoryId, String state) {
        log.info("查询文章列表 - pageNum: {}, pageSize: {}, categoryId: {}, state: {}", pageNum, pageSize, categoryId, state);
        
        PageHelper.startPage(pageNum, pageSize);
        Map<String,Object> claims=ThreadLocalUtil.get();
        log.info("ThreadLocal中的用户信息: {}", claims);
        
        int uid = (int)claims.get("id");
        log.info("当前用户ID: {}", uid);
        
        List<Article> articles = articleMapper.list(categoryId,state,uid);
        log.info("查询到的文章数量: {}", articles.size());
        
        PageBean<Article> pageBean = new PageBean<>();
        if (articles instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<Article> page = (com.github.pagehelper.Page<Article>) articles;
            pageBean.setTotal(page.getTotal());
            pageBean.setItems(page.getResult());
            log.info("分页信息 - 总数: {}, 当前页数据量: {}", page.getTotal(), page.getResult().size());
        } else {
            // 如果分页未生效，手动设置
            pageBean.setTotal((long) articles.size());
            pageBean.setItems(articles);
            log.warn("PageHelper未生效，使用手动分页");
        }

        return pageBean;
    }

    @Override
    public Article detail(int id) {
        return articleMapper.detail(id);

    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.update(article);
    }

    @Override
    public void delete(int id) {
        articleMapper.delete(id);
    }
}
