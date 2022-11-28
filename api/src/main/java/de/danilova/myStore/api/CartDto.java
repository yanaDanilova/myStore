package de.danilova.myStore.api;



import java.math.BigDecimal;
import java.util.List;



public class CartDto {
    private List<CartItemDto> cartItemsList;
    private BigDecimal sum;


    public List<CartItemDto> getCartItemsList() {
        return cartItemsList;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setCartItemsList(List<CartItemDto> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
