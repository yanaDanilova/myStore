package de.danilova.myStore.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
@Schema(description = "Product's model")
public class ProductDto {
    @Schema(description = "product's identifier", required = true, example = "1")
    private Long id;
    @Schema(description = "product's name", required = true, example = "T-Shirt")
    private String title;
    @Schema(description = "product's price", required = true, example = "103.00")
    private BigDecimal price;
    @Schema(description = "name of product's category", required = true, example = "Clothes")
    private String categoryTitle;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
