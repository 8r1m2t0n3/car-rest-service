package com.foxminded.car_rest_service.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foxminded.car_rest_service.model.Category;
import com.foxminded.car_rest_service.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	
	private Logger logger = LoggerFactory.getLogger(CategoryService.class);

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional
	public Category save(Category category) {
		categoryRepository.save(category);
		logger.info("Saved category with name: {}", category.getName());
		return category;
	}

	public Optional<Category> getById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}
	
	public Optional<Category> getByName(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}

	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	@Transactional
	public Category update(Category category, Long categoryId) {
		category.setId(categoryId);
		categoryRepository.save(category);
		logger.info("Updated category with id: {}", category.getId());
		return category;
	}

	@Transactional
	public void deleteById(Long categoryId) {
		categoryRepository.deleteById(categoryId);
		logger.info("Deleted category with id: {}", categoryId);
	}
}
