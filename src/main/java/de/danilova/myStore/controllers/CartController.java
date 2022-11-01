package de.danilova.myStore.controllers;

import de.danilova.myStore.dtos.CartDto;
import de.danilova.myStore.utils.Cart;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final Cart cart;



    @GetMapping
    public CartDto getCart(){
        return new CartDto(cart);
    }

    @GetMapping("/delete")
    public void deleteProductFromCartById(@RequestParam Long id){
        cart.deleteProductFromCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clearCart();
    }

    @GetMapping("/add")
    public void addProductToCart(@RequestParam Long id){
        cart.addProduct(id);
    }
}
