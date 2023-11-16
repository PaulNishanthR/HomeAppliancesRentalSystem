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
public class OrderStatusRequest {
    @NotNull
    @Min(value = 1, message = "orderId must be greater than or equal to 1")
    private Long orderId;

    @NotNull
    @Min(value = 1, message = "statusId must be greater than or equal to 1")
    private Long statusId;
}
