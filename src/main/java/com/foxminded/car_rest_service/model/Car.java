package com.foxminded.car_rest_service.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Unique id for DB")
	private Long id;

	@Column(name = "object_id")
	@Schema(description = "Unique object id")
	private String objectId;

	@Column(name = "model")
	@Size(min = 0, max = 64)
	private String model;

	@Column(name = "brand")
	@Size(min = 0, max = 32)
	private String brand;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "car_category", joinColumns = @JoinColumn(name = "car_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();

	@Column(name = "year")
	@Schema(description = "Year of manufacture")
	private Year year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void removeCategory(Category category) {
		categories.remove(category);
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Car))
			return false;
		Car car = (Car) o;
		return Objects.equals(this.objectId, car.objectId) && Objects.equals(this.model, car.model)
				&& Objects.equals(this.brand, car.brand) && Objects.equals(this.categories, car.categories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.objectId, this.model, this.brand, this.categories);
	}
}
