package de.danilova.myStore.core.controllers;

import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.entities.User;
import de.danilova.myStore.core.services.OrderService;
import de.danilova.myStore.core.services.UserService;
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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){ // @RequestBody  OrderData
        User user = userService.findUserByUsername(principal.getName()).orElseThrow(()->new ResourceNotFoundException("User doesn't find"));
        orderService.createOrder(user);
    }

}
