package com.nijimas.api.controller;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.application.user.UpdateParam;
import com.nijimas.api.core.exception.ApiErrorResponse;
import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import com.nijimas.api.util.UserUtil;
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
    public ResponseEntity<?> registerUser(@RequestBody @Valid CreateParam createParam) {
        try {
            UserEntity user = userService.createUser(createParam);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiErrorResponse(e));
        }

    }

    @PutMapping
    public ResponseEntity<?> updateUser(
            @RequestBody @Valid UpdateParam updateParam,
            @RequestAttribute("ownUid") String ownUid) {
        UserUtil.checkUid(updateParam.getUid(), ownUid);
        userService.updateUser(updateParam);
        return ResponseEntity.noContent().build();
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
