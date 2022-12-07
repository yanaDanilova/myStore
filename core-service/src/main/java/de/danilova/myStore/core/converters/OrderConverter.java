package de.danilova.myStore.core.converters;

import de.danilova.myStore.api.OrderDto;
import de.danilova.myStore.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemsConverter orderItemsConverter;

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUsername(order.getUsername());
        orderDto.setOrderItemsDtoList(order.getOrderItemsList().stream().map(orderItemsConverter::entityToDto).collect(Collectors.toList()));
        orderDto.setAddress(order.getAddress());
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;
    }
}
