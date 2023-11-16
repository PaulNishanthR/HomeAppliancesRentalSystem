package com.restapi.controller;

import com.restapi.model.Product;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts() {
        List<Product> productList = productService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
