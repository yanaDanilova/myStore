package de.danilova.myStore.controllers;

import de.danilova.myStore.dtos.CartDto;
import de.danilova.myStore.entities.User;
import de.danilova.myStore.exceptions.ResourceNotFoundException;
import de.danilova.myStore.services.OrderService;
import de.danilova.myStore.services.UserService;
import de.danilova.myStore.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final CartController cartController;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){
        User user = userService.findUserByUsername(principal.getName()).orElseThrow(()->new ResourceNotFoundException("User doesn't find"));
        orderService.createOrder(user, cartController.getCart());
    }

}
