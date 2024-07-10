package com.nijimas.api.controller;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    @PostMapping(path = "/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateParam createParam) {
        User user = userService.createUser(createParam);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(path = "/users/{uid}")
    public ResponseEntity<?> getUserByUid(@PathVariable String uid) {
        User user = userService.findByUid(uid);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
