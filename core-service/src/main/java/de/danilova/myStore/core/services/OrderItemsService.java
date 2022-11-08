package de.danilova.myStore.core.services;

import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.entities.OrderItems;
import de.danilova.myStore.core.repositories.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public OrderItems getOrderItemsById (Long id){
        return orderItemsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order Items with id "+id+" can't find"));
    }

    public void deleteOrderItemsById(Long id){
        orderItemsRepository.deleteById(id);
    }

    public void addOrderItems(OrderItems orderItems){
        orderItemsRepository.save(orderItems);
    }

    public void deleteAllOrderItems(){
        orderItemsRepository.deleteAll();
    }

    public void updateOrderItems(OrderItems orderItems){
        orderItemsRepository.save(orderItems);
    }

    public List<OrderItems> getAllOrderItems(){
        return orderItemsRepository.findAll();
    }
}
