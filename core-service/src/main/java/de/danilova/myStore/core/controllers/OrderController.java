package de.danilova.myStore.core.controllers;
import de.danilova.myStore.api.OrderDto;
import de.danilova.myStore.core.converters.OrderConverter;
import de.danilova.myStore.core.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name="Orders", description = "Methods of working with orders")
public class OrderController {


    private final OrderConverter orderConverter;
    private final OrderService orderService;

    @Operation(
            summary = "Request to receive all  user orders by username",
            responses = {
                    @ApiResponse(
                            description = "Successful responce", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username){
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());

    }


    @Operation(
            summary = "Request to create order",
            responses = {
                    @ApiResponse(
                            description = "Successfully created", responseCode = "201"
                    )
            }
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){ // @RequestBody  OrderData
        orderService.createOrder(username);
    }



}
