package de.danilova.myStore.dtos;

import de.danilova.myStore.entities.Product;
import de.danilova.myStore.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor

public class CartDto {
    private List<OrderItemsDto> cartList;
    private BigDecimal sum;

    public CartDto(Cart cart){
        this.cartList = cart.getCartList().stream().map(OrderItemsDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }
}
