package de.danilova.myStore.services;

import de.danilova.myStore.converters.OrderItemsConverter;
import de.danilova.myStore.dtos.CartDto;
import de.danilova.myStore.dtos.OrderItemsDto;
import de.danilova.myStore.entities.Order;
import de.danilova.myStore.entities.OrderItems;
import de.danilova.myStore.entities.Product;
import de.danilova.myStore.entities.User;
import de.danilova.myStore.repositories.OrderRepository;
import de.danilova.myStore.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

   private final OrderItemsConverter orderItemsConverter;
   private final OrderRepository orderRepository;

    public void createOrder(User user, CartDto cartDto){
        List<OrderItems> orderItemsList= cartDto.getCartList().stream().map(orderItemsConverter::dtosToEntities).collect(Collectors.toList());
        Order order = new Order(user,orderItemsList, cartDto.getSum());
        orderRepository.save(order);

    }
}
