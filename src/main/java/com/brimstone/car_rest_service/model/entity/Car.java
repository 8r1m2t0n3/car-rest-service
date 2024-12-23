package com.brimstone.car_rest_service.model.entity;

import com.brimstone.car_rest_service.model.enums.DriveType;
import com.brimstone.car_rest_service.model.enums.EngineType;
import com.brimstone.car_rest_service.model.enums.SteeringLocation;
import com.brimstone.car_rest_service.model.enums.TransmissionType;
import com.brimstone.car_rest_service.util.enum_converter.DriveTypeConverter;
import com.brimstone.car_rest_service.util.enum_converter.EngineTypeConverter;
import com.brimstone.car_rest_service.util.enum_converter.SteeringLocationConverter;
import com.brimstone.car_rest_service.util.enum_converter.TransmissionTypeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String model;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id")
  private Brand brand;

  private Year year;

  @Column(name = "price_in_usd")
  private BigDecimal price;

  @Convert(converter = TransmissionTypeConverter.class)
  private TransmissionType transmissionType;

  @Convert(converter = EngineTypeConverter.class)
  private EngineType engineType;

  @Convert(converter = DriveTypeConverter.class)
  private DriveType driveType;

  @Convert(converter = SteeringLocationConverter.class)
  private SteeringLocation steeringLocation;

  @Column(name = "mileage_in_km")
  private BigDecimal mileage;

  @Column(name = "color_rgb")
  private Integer color;

  private String ownerName;

  @Size(max = 17)
  private String vin;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(
      name = "car_category",
      joinColumns = @JoinColumn(name = "car_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();
}
