package de.danilova.myStore.cart.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void incrementQuantity() {
        quantity++;
        price = pricePerProduct.multiply(new BigDecimal(quantity));
    }

    public void decrementQuantity() {
        quantity--;
        price = pricePerProduct.multiply(new BigDecimal(quantity));
    }
}
