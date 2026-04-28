package org.example.servier.Imp;

import jakarta.annotation.Resource;
import org.example.entity.Category;
import org.example.mapper.CategoryMapper;
import org.example.mapper.UserMapper;
import org.example.servier.CategoryService;
import org.example.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImp implements CategoryService {


    @Resource
    CategoryMapper categoryMapper;


    @Override
    public void addCategory(Category category) {
        Map<String, Object> user = ThreadLocalUtil.get();
        int id = (int) user.get("id");


        categoryMapper.addCategory(category,id, LocalDateTime.now(),LocalDateTime.now());

    }

    @Override
    public List<Category> listCategory() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        return categoryMapper.listCategory( id);

    }

    @Override
    public Category getCategoryDetailByID(int id) {
        return categoryMapper.getCategoryDetailByID(id);

    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);

    }

    @Override
    public void deleteCategory(int id) {
        categoryMapper.deleteCategory(id);
    }
}
