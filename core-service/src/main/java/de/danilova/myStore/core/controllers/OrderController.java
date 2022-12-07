package de.danilova.myStore.core.controllers;
import de.danilova.myStore.core.services.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor

public class OrderController {


    private final OrderService orderService;


    @GetMapping
    public int showOrders(){
       return 1;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){ // @RequestBody  OrderData
        orderService.createOrder(username);
    }

}
