package de.danilova.myStore.api;

import jdk.jfr.DataAmount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemsDto> orderItemsDtoList;
    private BigDecimal totalPrice;
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
