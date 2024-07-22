package com.smit.categoryms.category;

import java.util.List;

public interface CategoryService {

	Category addCategory(Category category);

	Category updateCategory(Category category, Integer categoryId);

	List<Category> getCategories();

	Category getCategoryById(Integer categoryId);

	Boolean removeCategory(Integer categoryId);

}
