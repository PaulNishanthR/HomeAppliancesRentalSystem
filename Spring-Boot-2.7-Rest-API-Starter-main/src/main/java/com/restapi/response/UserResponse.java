package com.restapi.response;

import com.restapi.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private AppUser appUser;
}
