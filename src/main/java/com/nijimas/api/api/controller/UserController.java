package com.nijimas.api.api.controller;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.application.user.UpdateParam;
import com.nijimas.api.core.exception.ApiErrorResponse;
import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.service.UserService;
import com.nijimas.api.util.ControllerUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid CreateParam param,
            @RequestAttribute("ownUid") String ownUid) {
        try {
            param.setUid(ownUid);
            URI location = ControllerUtil.createLocation(ownUid);
            return ResponseEntity.created(location).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiErrorResponse(e));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(
            @RequestBody @Valid UpdateParam param,
            @RequestAttribute("ownUid") String ownUid) {
        param.setUid(ownUid);
        try {
            userService.updateUser(param);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorResponse(e));
        }
    }

    @GetMapping(path = "/{uid}")
    public ResponseEntity<?> getUserByUid(@PathVariable String uid) {
        try {
            UserEntity user = userService.findByUid(uid);
            return ResponseEntity.ok().body(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorResponse(e));
        }
    }
}
