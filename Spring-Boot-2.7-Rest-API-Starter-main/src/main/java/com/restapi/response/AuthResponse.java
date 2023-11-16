package com.restapi.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AuthResponse {
    private Long id;
    private String username;
    private String name;
}
