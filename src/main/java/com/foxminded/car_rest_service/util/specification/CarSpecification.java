package com.foxminded.car_rest_service.util.specification;

import com.foxminded.car_rest_service.model.dto.car.CarSortingOptionsDto;
import com.foxminded.car_rest_service.model.entity.Car;
import com.foxminded.car_rest_service.model.entity.Category;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

  public static Specification<Car> withSortOptions(CarSortingOptionsDto carSortingOptionsDto) {
    return (root, query, builder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (carSortingOptionsDto.getBrand() != null) {
        predicates.add(builder.equal(root.get("brand"), carSortingOptionsDto.getBrand()));
      }

      if (carSortingOptionsDto.getModel() != null) {
        predicates.add(builder.equal(root.get("model"), carSortingOptionsDto.getModel()));
      }

      if (carSortingOptionsDto.getManufactureYearRange() != null) {
        predicates.add(
            builder.between(
                root.get("year"),
                carSortingOptionsDto.getManufactureYearRange().getStart(),
                carSortingOptionsDto.getManufactureYearRange().getEnd()));
      }

      if (carSortingOptionsDto.getCategoryNames() != null
          && !carSortingOptionsDto.getCategoryNames().isEmpty()) {
        Join<Car, Category> categoryJoin = root.join("categories");

        Predicate[] categoryPredicates =
            carSortingOptionsDto.getCategoryNames().stream()
                .map(categoryName -> builder.equal(categoryJoin.get("name"), categoryName))
                .toArray(Predicate[]::new);

        Predicate categoryPredicate = builder.or(categoryPredicates);

        predicates.add(categoryPredicate);

        query.groupBy(root.get("id"));
        query.having(
            builder.equal(
                builder.countDistinct(categoryJoin.get("id")),
                carSortingOptionsDto.getCategoryNames().size()));
      }

      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
