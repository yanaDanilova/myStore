package de.danilova.myStore.api;


import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
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
