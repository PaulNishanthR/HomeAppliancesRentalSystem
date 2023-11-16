package com.restapi.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {
    @NotNull
    @Min(value = 1, message = "Id must be greater than or equal to 1")
    private Long id;

    @NotNull
    @Min(value = 1, message = "userId must be greater than or equal to 1")
    private Long userId;

    @NotEmpty
    @Size(min = 3, message = "Address at least have 3 characters")
    private String address;

    @NotEmpty
    @Size(min = 2, message = "City name at least have 2 characters")
    private String city;

    @NotNull
    @Min(value = 6, message = "Zipcode must be equal to 6 digits")
    private Integer zipcode;
}
