package de.danilova.myStore.cart.services;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.cart.integrations.ProductServiceIntegration;
import de.danilova.myStore.cart.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@RequiredArgsConstructor
public class CartService {


    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();

    }

    public Cart getCurrentCart(String uuid){
        return cart;
    }

    public void addToCart(String uuid, Long productId){
       ProductDto productDto = productServiceIntegration.getProductById(productId);
        cart.addProduct(productDto);
    }
    public void clear(String uuid){
        cart.clear();
    }

    public void removeFromCart(String uuid, Long productId){
        cart.removeProduct(productId);
    }

}
