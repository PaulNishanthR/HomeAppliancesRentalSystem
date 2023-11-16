package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull
    @Min(value = 1, message = "userId should not be empty")
    private Long userId;

    @NotNull
    @Min(value = 1, message = "addressId should not be empty")
    private Long addressId;
}
