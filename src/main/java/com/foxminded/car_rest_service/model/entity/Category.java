package com.foxminded.car_rest_service.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique id in DB")
  private Long id;

  @Column(name = "name")
  @Size(min = 0, max = 32)
  @Schema(description = "Category name")
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Category category)) return false;
    return Objects.equals(this.id, category.id) && Objects.equals(this.name, category.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }
}
