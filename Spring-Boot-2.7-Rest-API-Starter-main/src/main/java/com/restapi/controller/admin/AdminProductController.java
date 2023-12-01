package com.restapi.controller.admin;

import com.restapi.model.Product;
import com.restapi.model.Role;
import com.restapi.request.ProductRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProductService;
import com.restapi.service.StorageService;
import com.restapi.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
@RolesAllowed(Role.ADMIN)
public class AdminProductController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

//    @GetMapping("/{name}")
//    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
//        Product product = productService.getProduct(name);
//        return ResponseEntity
//                .ok()
//                .body(ImageUtils.decompressImage(product.getPhoto()));
//    }


    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts() {
        List<Product> productList = productService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createProduct(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("categoryId") String categoryId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") String price
    ) throws IOException {
        String photo1 = storageService.storeFile(photo);

        Product product = new Product();
        product.setPrice(Double.valueOf(price));
        product.setTitle(title);
        product.setDescription(description);
        product.setPhoto(photo1);

        List<Product> productList = productService.createProduct(product, Long.valueOf(categoryId));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(productList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateProduct(
            @RequestParam("image") MultipartFile file,
            @RequestBody ProductRequest productRequest) {
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

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        File file = productService.getFile(id);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
