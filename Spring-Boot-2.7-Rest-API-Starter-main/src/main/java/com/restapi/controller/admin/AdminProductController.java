package com.restapi.controller.admin;

import com.restapi.model.Product;
import com.restapi.model.Role;
import com.restapi.request.ProductRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
@RolesAllowed(Role.ADMIN)
public class AdminProductController {
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

    @PostMapping
    public ResponseEntity<APIResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        List<Product> productList = productService.createProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateProduct(@RequestBody @Valid ProductRequest productRequest) {
        List<Product> productList = productService.updateProduct(productRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Integer id) {
        List<Product> productList = productService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
