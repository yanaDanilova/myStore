package de.danilova.myStore.dtos;

import de.danilova.myStore.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor

public class ProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;

    public ProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();

    }
}
