package org.example.servier;

import org.example.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> listCategory();

    Category getCategoryDetailByID(int id);

    void updateCategory(Category category);

    void deleteCategory(int id);

}
