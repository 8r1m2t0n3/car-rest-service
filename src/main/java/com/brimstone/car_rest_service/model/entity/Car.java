package com.brimstone.car_rest_service.model.entity;

import com.brimstone.car_rest_service.model.enums.DriveType;
import com.brimstone.car_rest_service.model.enums.EngineType;
import com.brimstone.car_rest_service.model.enums.SteeringLocation;
import com.brimstone.car_rest_service.model.enums.TransmissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique id in DB")
  private Long id;

  @Schema(description = "Unique sequence of characters and numbers")
  private String objectId;

  @Size(min = 0, max = 64)
  private String model;

  @Size(min = 0, max = 32)
  private String brand;

  @Schema(description = "Year of manufacture")
  private Year year;

  @Column(name = "price_in_usd")
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  private TransmissionType transmissionType;

  @Enumerated(EnumType.STRING)
  private EngineType engineType;

  @Enumerated(EnumType.STRING)
  private DriveType driveType;

  @Enumerated(EnumType.STRING)
  private SteeringLocation steeringLocation;

  @Column(name = "mileage_in_km")
  private BigDecimal mileage;

  @Column(name = "color_rgb")
  private Integer color;

  @Size(max = 128)
  private String ownerName;

  @Size(max = 17)
  private String vin;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(
      name = "car_category",
      joinColumns = @JoinColumn(name = "car_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;
}
