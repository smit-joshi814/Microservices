package com.smit.categoryms.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/category")
class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	ResponseEntity<List<Category>> getCategories() {
		return ResponseEntity.ok(categoryService.getCategories());
	}

	@GetMapping("{id}")
	ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PostMapping
	ResponseEntity<Category> addcategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.addCategory(category));
	}

	@PutMapping("{id}")
	ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Integer id) {
		return ResponseEntity.ok(categoryService.updateCategory(category, id));
	}

	@DeleteMapping("{id}")
	ResponseEntity<Boolean> deleteCategory(@PathVariable Integer id) {
		return ResponseEntity.ok(categoryService.removeCategory(id));
	}
	
}
