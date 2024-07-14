package com.nijimas.api.controller;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.exception.ApiErrorResponse;
import com.nijimas.api.exception.UserAlreadyExistsException;
import com.nijimas.api.exception.UserNotFoundException;
import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateParam createParam) {
        try {
            User user = userService.createUser(createParam);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(new ApiErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
        }

    }

    @GetMapping(path = "/{uid}")
    public ResponseEntity<?> getUserByUid(@PathVariable String uid) {
        try {
            User user = userService.findByUid(uid);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.NOT_FOUND);
        }
    }
}
