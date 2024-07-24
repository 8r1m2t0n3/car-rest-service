package com.foxminded.car_rest_service.model.entity;

import java.time.Year;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique id in DB")
  private Long id;

  @Column(name = "object_id")
  @Schema(description = "Unique sequence of characters and numbers")
  private String objectId;

  @Column(name = "model")
  @Size(min = 0, max = 64)
  private String model;

  @Column(name = "brand")
  @Size(min = 0, max = 32)
  private String brand;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(
      name = "car_category",
      joinColumns = @JoinColumn(name = "car_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;

  @Column(name = "year")
  @Schema(description = "Year of manufacture")
  private Year year;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Car car)) return false;
    return Objects.equals(this.objectId, car.objectId)
        && Objects.equals(this.model, car.model)
        && Objects.equals(this.brand, car.brand)
        && Objects.equals(this.categories, car.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.objectId, this.model, this.brand, this.categories);
  }
}
