package de.danilova.myStore.dtos;

import de.danilova.myStore.entities.OrderItems;
import de.danilova.myStore.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemsDto {
    private Long orderItemsId;
    private String productTitle;
    private Long productId;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemsDto(OrderItems orderItems){
        this.orderItemsId = orderItems.getId();
        this.productTitle= orderItems.getProduct().getTitle();
        this.productId = orderItems.getProduct().getId();
        this.quantity = orderItems.getQuantity();
        this.pricePerProduct = orderItems.getPricePerProduct();
        this.price = orderItems.getPrice();
    }

}
