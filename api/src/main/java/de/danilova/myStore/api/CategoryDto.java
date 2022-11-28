package de.danilova.myStore.api;

import java.util.List;

public class CategoryDto {

    private Long id;
    private String title;
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
