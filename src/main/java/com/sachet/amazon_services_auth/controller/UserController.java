package com.sachet.amazon_services_auth.controller;

import com.sachet.amazon_services_auth.model.Address;
import com.sachet.amazon_services_auth.model.User;
import com.sachet.amazon_services_auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/addAddress/{userId}")
    public ResponseEntity<Address> addAddress(@RequestBody Address address,
                                              @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.addAddress(userId, address));
    }

}
