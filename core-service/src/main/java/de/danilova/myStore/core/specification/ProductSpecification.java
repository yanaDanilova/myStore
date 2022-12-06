package de.danilova.myStore.core.specification;

import de.danilova.myStore.core.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> priceGreaterOrEqualsThen(BigDecimal minPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice));
    }

    public static Specification<Product> priceLesserOrEqualsThen(BigDecimal maxPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice));
    }

    public static Specification<Product> titleLike(String partTitle){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",partTitle)));
    }
}
