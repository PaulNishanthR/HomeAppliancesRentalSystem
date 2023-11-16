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
public class CategoryRequest {

    @NotNull
    @Min(value = 1, message = "Id must be greater than or equal to 1")
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "Category title at least have 3 characters")
    private String title;

    public CategoryRequest(String title) {
        this.title = title;
    }
}
