package de.danilova.myStore.api;

import java.math.BigDecimal;

public class OrderItemsDto {
    private Long id;
    private String productTitle;
    private Long orderId;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getId() {
        return id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public Long getOrderId() {
        return orderId;
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
}
