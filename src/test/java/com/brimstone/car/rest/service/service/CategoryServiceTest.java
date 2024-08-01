//package com.foxminded.car_rest_service.service;
//
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.brimstone.car.rest.service.model.entity.Category;
//import com.brimstone.car.rest.service.repository.CategoryRepository;
//
//@ExtendWith(MockitoExtension.class)
//class CategoryServiceTest {
//
//	@Mock
//	CategoryRepository categoryRepository;
//
//	@InjectMocks
//	CategoryService categoryService;
//
//	@Test
//	void saveTest() {
//		Category category = new Category();
//		when(categoryRepository.save(category)).thenReturn(category);
//
//		categoryService.save(category);
//
//		verify(categoryRepository).save(category);
//	}
//
//	@Test
//	void getByIdTest() {
//		when(categoryRepository.findById(1L)).thenReturn(Optional.of(new Category()));
//
//		categoryService.getById(1L);
//
//		verify(categoryRepository).findById(1L);
//	}
//	@Test
//	void getByNameTest() {
//		when(categoryRepository.findByName("name")).thenReturn(Optional.of(new Category()));
//
//		categoryService.getByName("name");
//
//		verify(categoryRepository).findByName("name");
//	}
//
//	@Test
//	void getAllTest() {
//		when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
//
//		categoryService.getAll();
//
//		verify(categoryRepository).findAll();
//	}
//
//	@Test
//	void updateTest() {
//		Category category = new Category();
//		when(categoryRepository.save(category)).thenReturn(category);
//
//		categoryService.update(category, 1L);
//
//		verify(categoryRepository).save(category);
//	}
//
//	@Test
//	void deleteByIdTest() {
//		doNothing().when(categoryRepository).deleteById(1L);
//
//		categoryService.deleteById(1L);
//
//		verify(categoryRepository).deleteById(1L);
//	}
//}
