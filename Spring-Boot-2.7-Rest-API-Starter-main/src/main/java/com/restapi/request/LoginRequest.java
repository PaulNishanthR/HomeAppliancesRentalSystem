package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 3, message = "Password should have at least 3 characters")
    private String password;
}
