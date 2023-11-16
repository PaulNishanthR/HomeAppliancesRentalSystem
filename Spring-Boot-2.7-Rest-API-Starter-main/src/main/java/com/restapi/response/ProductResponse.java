package com.restapi.response;

import com.restapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private List<Product> productList;
}
