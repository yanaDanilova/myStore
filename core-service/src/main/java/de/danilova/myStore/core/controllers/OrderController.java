package de.danilova.myStore.core.controllers;
import de.danilova.myStore.api.OrderDto;
import de.danilova.myStore.core.converters.OrderConverter;
import de.danilova.myStore.core.services.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor

public class OrderController {


    private final OrderConverter orderConverter;
    private final OrderService orderService;


    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username){
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){ // @RequestBody  OrderData
        orderService.createOrder(username);
    }



}
