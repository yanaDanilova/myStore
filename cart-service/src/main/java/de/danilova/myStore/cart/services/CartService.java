package de.danilova.myStore.cart.services;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.cart.integrations.ProductServiceIntegration;
import de.danilova.myStore.cart.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class CartService {


    private final RedisTemplate<String,Object> redisTemplate;
    private final ProductServiceIntegration productServiceIntegration;


    private String cartPrefix = "myStoreCart";

    public Cart getCurrentCart(String uuid){
        String targetUuid = cartPrefix+uuid;
        if(!redisTemplate.hasKey(targetUuid)){
            redisTemplate.opsForValue().set(targetUuid,new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void addToCart(String uuid, Long productId){
       ProductDto productDto = productServiceIntegration.getProductById(productId);
        execute(uuid,cart -> cart.addProduct(productDto));
    }
    public void clear(String uuid){
        execute(uuid, Cart::clear);
    }

    public void removeFromCart(String uuid, Long productId){
        execute(uuid,cart -> cart.removeProduct(productId));
    }

    private void execute(String uuid,Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix+uuid,cart);
    }
}
