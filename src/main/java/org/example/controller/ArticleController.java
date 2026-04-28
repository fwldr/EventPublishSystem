package org.example.controller;

import jakarta.annotation.Resource;
import org.example.entity.Article;
import org.example.entity.PageBean;
import org.example.entity.Result;
import org.example.servier.ArticleService;
import org.example.servier.Imp.ArticleServiceImp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/article")
@RestController
public class ArticleController {

    @Resource
    ArticleService articleService;

    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article){

        articleService.addArticle(article);
        return Result.success("添加文章成功");
    }
    @GetMapping
    public Result<PageBean<Article>> list(
            int pageNum,
            int pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state

    ){
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);


    }
    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam int id){
        Article article = articleService.detail(id);
        return Result.success(article);
    }

    @PutMapping
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success("更新文章成功");
    }
    @DeleteMapping
    public Result delete(@RequestParam int id){
        articleService.delete(id);
        return Result.success("删除文章成功");
    }
}
