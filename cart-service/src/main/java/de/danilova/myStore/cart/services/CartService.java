package de.danilova.myStore.cart.services;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.cart.integrations.ProductServiceIntegration;
import de.danilova.myStore.cart.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CartService {


    private final ProductServiceIntegration productServiceIntegration;
    private Map<String,Cart> carts;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();

    }

    public Cart getCurrentCart(String uuid){
        String targetUuid = cartPrefix+uuid;
        if(!carts.containsKey(targetUuid)){
            carts.put(targetUuid,new Cart());
        }
        return carts.get(targetUuid);
    }

    public void addToCart(String uuid, Long productId){
       ProductDto productDto = productServiceIntegration.getProductById(productId);
       getCurrentCart(uuid).addProduct(productDto);

    }
    public void clear(String uuid){
        getCurrentCart(uuid).clear();

    }

    public void removeFromCart(String uuid,Long productId){
        getCurrentCart(uuid).removeProduct(productId);
    }

}
