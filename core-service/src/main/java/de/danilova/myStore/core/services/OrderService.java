package de.danilova.myStore.core.services;

import de.danilova.myStore.api.CartDto;
import de.danilova.myStore.api.ResourceNotFoundException;

import de.danilova.myStore.core.entities.OrderItems;
import de.danilova.myStore.core.integrations.CartServiceIntegration;
import de.danilova.myStore.core.repositories.OrderRepository;
import de.danilova.myStore.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private  final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
   private final OrderRepository orderRepository;

   @Transactional
    public Order createOrder(String username){
        CartDto cartDto = cartServiceIntegration.getCurrentCartDto(username);
       Order order = new Order();
       order.setUsername(username);
       order.setTotalPrice(cartDto.getSum());
       order.setOrderItemsList(cartDto.getCartItemsList().stream().map(cartItemDto -> new OrderItems(
               productService.getProductById(cartItemDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't found")),
               cartItemDto.getPricePerProduct(),
               cartItemDto.getQuantity(),
               cartItemDto.getPrice(),order)).collect(Collectors.toList()));
        orderRepository.save(order);

        cartServiceIntegration.clear(username);
        return order;
    }

    public List<Order> findByUsername(String username){
       return orderRepository.findByUsername(username);
    }
}
