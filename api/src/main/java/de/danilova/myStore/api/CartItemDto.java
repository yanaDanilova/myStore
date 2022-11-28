package de.danilova.myStore.api;

import java.math.BigDecimal;

public class CartItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public Long getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
