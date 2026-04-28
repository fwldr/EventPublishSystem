package org.example.controller;


import jakarta.annotation.Resource;
import org.example.entity.Category;
import org.example.entity.Result;
import org.example.servier.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {
    @Resource
    CategoryService categoryService;


    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.addCategory(category);
        return Result.success();
    }
    @GetMapping
    public Result<List<Category>> listCategory(){
        List<Category> list = categoryService.listCategory();
        return Result.success(list);
    }

    @GetMapping("/detail")
    public Result<Category> getCategoryDetail(@RequestParam int id){
        Category category = categoryService.getCategoryDetailByID(id);
        return Result.success(category);
    }
    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategory(@RequestParam int id){
        categoryService.deleteCategory(id);
        return Result.success();
    }
}
