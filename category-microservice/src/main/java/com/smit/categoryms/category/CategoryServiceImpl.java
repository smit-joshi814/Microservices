package com.smit.categoryms.category;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category, Integer categoryId) {
		Optional<Category> dbCategoryOpt = categoryRepository.findById(categoryId);
		dbCategoryOpt.ifPresent(dbCategory -> {
			if (Objects.nonNull(category.getCategoryName()) && !"".equalsIgnoreCase(category.getCategoryName())) {
				dbCategory.setCategoryName(category.getCategoryName());
			}
		});
		return categoryRepository.save(dbCategoryOpt.get());
	}

	@Override
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}

	@Override
	public Boolean removeCategory(Integer categoryId) {
		Optional<Category> dbCategoryOpt = categoryRepository.findById(categoryId);
		if (dbCategoryOpt.isPresent()) {
			categoryRepository.delete(dbCategoryOpt.get());
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
