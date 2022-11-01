package de.danilova.myStore.converters;

import de.danilova.myStore.dtos.OrderItemsDto;
import de.danilova.myStore.entities.Order;
import de.danilova.myStore.entities.OrderItems;
import de.danilova.myStore.exceptions.ResourceNotFoundException;
import de.danilova.myStore.services.OrderItemsService;
import de.danilova.myStore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class OrderItemsConverter {
    private final ProductService productService;


    public OrderItemsDto entitiesToDtos(OrderItems orderItems){
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setProductTitle(orderItems.getProduct().getTitle());
        orderItemsDto.setProductId(orderItems.getProduct().getId());
        orderItemsDto.setPricePerProduct(orderItems.getPricePerProduct());
        orderItemsDto.setQuantity(orderItems.getQuantity());
        orderItemsDto.setPrice(orderItems.getPrice());
        return orderItemsDto;
    }

    public OrderItems dtosToEntities(OrderItemsDto orderItemsDto){
        OrderItems orderItems= new OrderItems();
        orderItems.setProduct(productService.getProductById(orderItemsDto.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product doesn't found")));
        orderItems.setPricePerProduct(orderItemsDto.getPricePerProduct());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setPrice(orderItemsDto.getPrice());
        return orderItems;
    }
}
