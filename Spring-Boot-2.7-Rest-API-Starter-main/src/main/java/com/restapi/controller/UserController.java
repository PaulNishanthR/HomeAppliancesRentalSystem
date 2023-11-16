package com.restapi.controller;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class UserController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserDetails(@Valid @PathVariable Integer userId) {
        AppUser appUser = userService.findUserById(Long.valueOf(userId));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<APIResponse> createAddress(@Valid @RequestBody
                                                     AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.create(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse.getAddressList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/address")
    public ResponseEntity<APIResponse> updateAddress(@Valid @RequestBody
                                                     AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.update(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse.getAddressList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<APIResponse> deleteAddress(@Valid @PathVariable Integer id) {
        AddressResponse addressResponse = addressService
                .deleteById(Long.valueOf(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse.getAddressList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
