package de.danilova.myStore.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Category's model")
public class CategoryDto {

    @Schema(description = "Category's identifier", required = true, example = "1")
    private Long id;
    @Schema(description = "Category's name", required = true, example = "Clothes")
    private String title;
    @Schema(description = "List of product this category", required = true)
    private List<ProductDto> products;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public CategoryDto(Long id, String title, List<ProductDto> products) {
        this.id = id;
        this.title = title;
        this.products = products;
    }
    public CategoryDto() {
    }


}
