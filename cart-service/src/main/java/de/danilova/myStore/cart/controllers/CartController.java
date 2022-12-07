package de.danilova.myStore.cart.controllers;


import de.danilova.myStore.api.CartDto;

import de.danilova.myStore.cart.converters.CartConverter;
import de.danilova.myStore.cart.services.CartService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")

public class CartController {

    private  final CartService cartService;
    private  final CartConverter cartConverter;



    @GetMapping
    public CartDto getCart(){
        return cartConverter.modelToDto(cartService.getCurrentCart());
    }

    @GetMapping("/delete")
    public void deleteProductFromCartById(@RequestParam Long id){
        cartService.removeFromCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cartService.addToCart(id);
    }
}
