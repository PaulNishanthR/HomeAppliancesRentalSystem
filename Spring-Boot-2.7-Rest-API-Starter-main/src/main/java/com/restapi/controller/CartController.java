package com.restapi.controller;

import com.restapi.model.Role;
import com.restapi.request.CartRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
@RolesAllowed(Role.USER)
public class CartController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersCart(@Valid @PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.findUserCart(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> addToCart(@Valid @RequestBody CartRequest cartRequest) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.addToCart(cartRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productCart}")
    public ResponseEntity<APIResponse> deleteProductFromCart(@Valid @PathVariable Long userId,
                                                          @PathVariable Long productCart) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.deleteProductFromCart(userId, productCart));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
