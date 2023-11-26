package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;

    private Long categoryId;

    @NotEmpty
    @Size(min = 2, message = "title at least have 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 3, message = "description at least have 3 characters")
    private String description;

    @NotNull
    @Min(value = 3, message = "price must be greater than or equal to 3 digits")
    private Double price;

    private byte[] photo;
}
