package com.restapi.dto;

import com.restapi.model.Product;
import com.restapi.request.ProductRequest;
import com.restapi.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductDto {

    public ProductResponse mapToProductResponse(List<Product> productList){
        return new ProductResponse(productList);
    }

    public Product mapToProduct(ProductRequest productRequest) {

        Product product = new Product();
        if (productRequest.getId() != null) {
            product.setId(productRequest.getId());
        }
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setTitle(productRequest.getTitle());
        product.setPhoto(productRequest.getPhoto());
        return product;
    }


}
