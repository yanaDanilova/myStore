package de.danilova.myStore.core.converters;
import de.danilova.myStore.api.OrderItemsDto;
import de.danilova.myStore.core.entities.OrderItems;
import org.springframework.stereotype.Component;

@Component
public class OrderItemsConverter {

    public OrderItemsDto entityToDto(OrderItems orderItems){
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setId(orderItems.getId());
        orderItemsDto.setOrderId(orderItems.getOrder().getId());
        orderItemsDto.setProductTitle(orderItems.getProduct().getTitle());
        orderItemsDto.setQuantity(orderItems.getQuantity());
        orderItemsDto.setPricePerProduct(orderItems.getPricePerProduct());
        orderItemsDto.setPrice(orderItems.getPrice());
        return orderItemsDto;
    }

    public OrderItems dtoToEntity(OrderItemsDto orderItemsDto){
        OrderItems orderItems = new OrderItems();

        return orderItems;
    }
}
