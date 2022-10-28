package de.danilova.myStore.utils;

import de.danilova.myStore.entities.OrderItems;
import de.danilova.myStore.entities.Product;
import de.danilova.myStore.exceptions.ResourceNotFoundException;
import de.danilova.myStore.services.OrderItemsService;
import de.danilova.myStore.services.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
@Data
@RequiredArgsConstructor
public class Cart {

    private final ProductService productService;
    private final OrderItemsService orderItemsService;
    private List<OrderItems> cartList;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        this.cartList = new ArrayList<>();

    }

    @Transactional
    public void addProduct(Long id){
        if(!cartList.isEmpty()) {
            for (OrderItems oi : cartList) {
                if (oi.getProduct().getId().equals(id)) {
                    oi.increment();
                    orderItemsService.updateOrderItems(oi);
                    updateCart();
                    return;
                }
            }
        }

            Product product = productService.getProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id: "+ id+" can't add to Cart"));
            OrderItems orderItems = new OrderItems(product);
            orderItemsService.addOrderItems(orderItems);
            updateCart();
        }




    private void updateCart() {
        cartList.clear();
        cartList.addAll(orderItemsService.getAllOrderItems());
        calculate();
    }

    public void calculate(){
        sum = BigDecimal.ZERO;
        for(OrderItems oi: cartList){
            sum = sum.add(oi.getPrice());
        }
    }

    public void deleteProductFromCart(Long id){
        for (OrderItems oi : cartList){
            if(oi.getProduct().getId().equals(id)){
                if(oi.getQuantity()< 1){
                    cartList.remove(oi);
                    orderItemsService.deleteOrderItemsById(oi.getId());
                    updateCart();
                    return;
                }
                oi.decrement();
                orderItemsService.updateOrderItems(oi);
                updateCart();
                return;
            }
        }

    }

    public void clearCart(){
        cartList.clear();
        calculate();
    }
    public List<OrderItems> getCartList(){
        return Collections.unmodifiableList(cartList);
    }

}
