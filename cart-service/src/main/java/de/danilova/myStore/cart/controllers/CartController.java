package de.danilova.myStore.cart.controllers;


import de.danilova.myStore.api.CartDto;

import de.danilova.myStore.api.StringResponse;
import de.danilova.myStore.cart.converters.CartConverter;
import de.danilova.myStore.cart.services.CartService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")

public class CartController {

    private  final CartService cartService;
    private  final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid(){
        return  new StringResponse(UUID.randomUUID().toString());
    }


    @GetMapping("/{uuid}")
    public CartDto getCart(@RequestHeader (name = "username",required = false) String username, @PathVariable String uuid){
       String targetUuid = getCartUuid(username,uuid);
        return cartConverter.modelToDto(cartService.getCurrentCart(targetUuid));
    }

    @GetMapping("{uuid}/delete/{id}")
    public void deleteProductFromCartById(@RequestHeader (name = "username",required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        String targetUuid = getCartUuid(username,uuid);
        cartService.removeFromCart(targetUuid,id);
    }

    @GetMapping("{uuid}/clear")
    public void clearCart(@RequestHeader (name = "username",required = false) String username, @PathVariable String uuid){
        String targetUuid = getCartUuid(username,uuid);
        cartService.clear(targetUuid);
    }

    @GetMapping("{uuid}/add/{id}")
    public void addProductToCart(@RequestHeader (name = "username",required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        String targetUuid = getCartUuid(username,uuid);
        cartService.addToCart(targetUuid,id);
    }

    private String getCartUuid(String username, String uuid){
        if(username != null){
            return username;
        }
        return uuid;
    }
}
