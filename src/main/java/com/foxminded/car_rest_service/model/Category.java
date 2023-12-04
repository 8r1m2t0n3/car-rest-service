package com.foxminded.car_rest_service.model;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Unique id for DB")
	private Long id;

	@Column(name = "name")
	@Size(min = 0, max = 32)
	@Schema(description = "Category name")
	private String name;

	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Category))
			return false;
		Category category = (Category) o;
		return Objects.equals(this.id, category.id) && Objects.equals(this.name, category.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}
}
