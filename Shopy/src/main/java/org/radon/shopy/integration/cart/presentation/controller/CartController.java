package org.radon.shopy.integration.cart.presentation.controller;

import org.radon.shopy.integration.cart.application.port.in.CheckForAddItemToCartUseCase;
import org.radon.shopy.integration.cart.application.port.in.CheckForRemoveItemFromCartUseCase;
import org.radon.shopy.integration.cart.application.port.in.GetCartUseCase;
import org.radon.shopy.integration.cart.presentation.controller.mappers.CartGrpcMappers;
import org.radon.shopy.integration.cart.presentation.controller.mappers.InventoryGrpcMappers;
import org.radon.shopy.integration.cart.presentation.dto.CartDto;
import org.radon.shopy.integration.cart.presentation.dto.CartItemDto;
import org.radon.shopy.integration.cart.presentation.dto.CheckForAddOrRemoveDto;
import org.radon.shopy.shared.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final GetCartUseCase getCartUseCase;
    private final CheckForAddItemToCartUseCase checkForAddItemToCartUseCase;
    private final CheckForRemoveItemFromCartUseCase checkForRemoveItemFromCartUseCase;


    public CartController(GetCartUseCase getCartUseCase, CheckForAddItemToCartUseCase checkForAddItemToCartUseCase, CheckForRemoveItemFromCartUseCase checkForRemoveItemFromCartUseCase) {
        this.getCartUseCase = getCartUseCase;
        this.checkForAddItemToCartUseCase = checkForAddItemToCartUseCase;
        this.checkForRemoveItemFromCartUseCase = checkForRemoveItemFromCartUseCase;
    }

    @GetMapping("")
    public ResponseEntity<Response<CartDto>> getCart() {
        return new ResponseEntity<>(new Response<CartDto>(CartGrpcMappers.toCartDto(getCartUseCase.getCart())),HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Response<CartItemDto>> addToCart(@RequestBody CheckForAddOrRemoveDto model) {
        return new ResponseEntity<>(new Response<CartItemDto>(CartGrpcMappers.toCartItemDto(checkForAddItemToCartUseCase.checkForAddItemToCart(model))),HttpStatus.OK);
    }

    @DeleteMapping("remove")
    public ResponseEntity<Response<CartItemDto>> removeFromCart(@RequestBody CheckForAddOrRemoveDto model) {
        return new ResponseEntity<>(new Response<CartItemDto>(CartGrpcMappers.toCartItemDto(checkForRemoveItemFromCartUseCase.checkForRemoveItemFromCart(model))),HttpStatus.OK);
    }

}
