package com.restapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {

    @NotNull
    @Min(value = 1, message = "userId must be greater than or equal to 1")
    private Long userId;

    @NotNull
    @Min(value = 1, message = "productId must be greater than or equal to 1")
    private Long productId;

    @NotNull
    @Min(value = 1, message = "Count must be greater than or equal to 1")
    private Integer count;
}
