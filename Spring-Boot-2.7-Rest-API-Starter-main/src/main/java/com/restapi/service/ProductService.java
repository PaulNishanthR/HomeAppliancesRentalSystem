package com.restapi.service;

import com.restapi.dto.ProductDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Product;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.ProductRepository;
import com.restapi.request.ProductRequest;
import com.restapi.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDto productDto;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService storageService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> createProduct(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", categoryId));
        product.setPhoto(product.getPhoto());
        product.setCategory(category);
        productRepository.save(product);
        return findAll();
    }

    @Transactional
    public List<Product> updateProduct(ProductRequest productRequest) {
        Product product = productDto.mapToProduct(productRequest);
        product.setPhoto(product.getPhoto());
        Category category = categoryRepository.findById(Long.valueOf(productRequest.getCategoryId())).orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
        return findAll();
    }


    public List<Product> deleteById(Integer id) {
        productRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public Product getProduct(String name) {
        Optional<Product> productOptional = productRepository.findByTitle(name);
        return productOptional.get();
    }

    public File getFile(Long id) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(product.getPhoto());

        return resource.getFile();
    }
}
