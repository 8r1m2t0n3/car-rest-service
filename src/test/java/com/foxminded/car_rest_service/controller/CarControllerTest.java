package com.foxminded.car_rest_service.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.model.entity.Category;
import com.foxminded.car_rest_service.service.CarService;
import com.foxminded.car_rest_service.service.CategoryService;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class CarControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CarService carService;

	@MockBean
	CategoryService categoryService;

	@Test
	void getAllTest() throws Exception {
		when(carService.getAll()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(carService).getAll();
	}

	@Test
	void getCarByObjectIdTest() throws Exception {
		Car car = new Car();
		car.setObjectId("AAAAA");
		when(carService.getByObjectId(car.getObjectId())).thenReturn(Optional.of(car));

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/object-id/AAAAA"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(carService).getByObjectId("AAAAA");
	}

	@Test
	void getCarsByBrandTest() throws Exception {
		Car car = new Car();
		car.setBrand("brand");
		when(carService.getByBrand(car.getBrand())).thenReturn(List.of(car));

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/brand/brand"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(carService).getByBrand("brand");
	}

	@Test
	void getByBrandAndModelTest() throws Exception {
		Car carModel = new Car();
		carModel.setModel("model");
		Car carNotModel = new Car();
		carNotModel.setModel("not_model");

		List<Car> cars = new ArrayList<>(Arrays.asList(carModel, carNotModel));
		when(carService.getByBrand("brand")).thenReturn(cars);

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/brand/brand/model/model"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		assertEquals(List.of(carModel), cars);

		verify(carService).getByBrand("brand");
	}

	@Test
	void getByMinYearAndMaxYearTest() throws Exception {
		when(carService.getByMinYearAndMaxYear(Year.of(2020), Year.of(2023))).thenReturn(new ArrayList<>());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cars/min-year/2020/max-year/2023"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(carService).getByMinYearAndMaxYear(Year.of(2020), Year.of(2023));
	}

	@Test
	void getByBrandAndMinYearAndMaxYearTest() throws Exception {
		Car carRightYear = new Car();
		carRightYear.setYear(Year.of(2020));
		Car carNotRightYear = new Car();
		carNotRightYear.setYear(Year.of(1999));

		List<Car> cars = new ArrayList<>(Arrays.asList(carRightYear, carNotRightYear));
		when(carService.getByBrand("brand")).thenReturn(cars);

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/brand/brand/min-year/2020/max-year/2023"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		assertEquals(List.of(carRightYear), cars);

		verify(carService).getByBrand("brand");
	}

	@Test
	void getByBrandAndModelAndMinYearAndMaxYearTest() throws Exception {
		Car carRightYearRightModel = new Car();
		carRightYearRightModel.setYear(Year.of(2020));
		carRightYearRightModel.setModel("model");
		Car carNotRightYearRightModel = new Car();
		carNotRightYearRightModel.setYear(Year.of(1999));
		carNotRightYearRightModel.setModel("model");
		Car carRigthYearNotRightModel = new Car();
		carRigthYearNotRightModel.setYear(Year.of(2021));
		carRigthYearNotRightModel.setModel("not_model");
		Car carNotRightYearNotRightModel = new Car();
		carNotRightYearNotRightModel.setYear(Year.of(1999));
		carNotRightYearNotRightModel.setModel("not_model");

		List<Car> cars = new ArrayList<>(Arrays.asList(carRightYearRightModel, carNotRightYearRightModel,
				carRigthYearNotRightModel, carNotRightYearNotRightModel));
		when(carService.getByBrand("brand")).thenReturn(cars);

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/brand/brand/model/model/min-year/2020/max-year/2023"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		assertEquals(List.of(carRightYearRightModel), cars);

		verify(carService).getByBrand("brand");
	}

	@Test
	void add_shouldNotAccess_whenUserUnauthenticated() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/cars?brand=brand&model=model&year=2020&categories=cat1,cat2,cat3"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void addTest() throws Exception {
		Category cat1 = new Category();
		cat1.setName("cat1");
		Category cat2 = new Category();
		cat2.setName("cat2");

		when(carService.generateUniqueObjectId()).thenReturn("AAAAA");
		
		when(categoryService.getByName("cat1")).thenReturn(Optional.of(cat1));
		when(categoryService.getByName("cat2")).thenReturn(Optional.of(cat2));
		when(categoryService.getByName("cat3")).thenReturn(Optional.empty());

		Car car = new Car();
		car.setObjectId("AAAAA");
		car.setBrand("brand");
		car.setModel("model");
		car.setYear(Year.of(2020));
		car.addCategory(cat1);
		car.addCategory(cat2);
		
		
		Category cat3 = new Category();
		cat3.setName("cat3");
		when(categoryService.save(cat3)).thenReturn(cat3);
		car.addCategory(cat3);

		when(carService.save(car)).thenReturn(car);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cars?brand=brand&model=model&year=2020&categories=cat1,cat2,cat3"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(carService).generateUniqueObjectId();
		verify(categoryService).getByName("cat1");
		verify(categoryService).getByName("cat2");
		verify(categoryService).getByName("cat3");
		verify(categoryService).save(cat3);
		verify(carService).save(car);
	}

	@Test
	void delete_shouldNotAccess_whenUserUnauthenticated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/cars?object-id=AAAA"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void deleteTest() throws Exception {
		doNothing().when(carService).deleteByObjectId("AAAA");

		mockMvc.perform(MockMvcRequestBuilders.delete("/cars?object-id=AAAA"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(carService).deleteByObjectId("AAAA");
	}
}
