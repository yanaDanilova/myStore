package de.danilova.myStore.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.DataAmount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Order's model")
public class OrderDto {
    @Schema(description = "Order's identifier", required = true, example = "1")
    private Long id;
    @Schema(description = "Name of user, who created this order", required = true, example = "Max Mustermann")
    private String username;
    @Schema(description = "List of order's row", required = true)
    private List<OrderItemsDto> orderItemsDtoList;
    @Schema(description = "Order's total price", required = true,example = "500.00")
    private BigDecimal totalPrice;
    @Schema(description = "The address to which the order should be sent ", required = true, example = "1")
    private String address;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderItemsDtoList(List<OrderItemsDto> orderItemsDtoList) {
        this.orderItemsDtoList = orderItemsDtoList;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<OrderItemsDto> getOrderItemsDtoList() {
        return orderItemsDtoList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getAddress() {
        return address;
    }
}
