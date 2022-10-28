package de.danilova.myStore.controllers;

import de.danilova.myStore.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final Cart cart;

    @GetMapping
    public Cart getCart(){
        return cart;
    }

    @DeleteMapping()
    public void deleteProductFromCartById(@RequestParam Long id){
        cart.deleteProductFromCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clearCart();
    }

    @GetMapping("{id}")
    public void addProductToCart(@PathVariable Long id){
        cart.deleteProductFromCart(id);
    }
}
