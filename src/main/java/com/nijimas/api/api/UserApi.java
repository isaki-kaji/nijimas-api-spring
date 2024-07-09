package com.nijimas.api.api;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserApi {
    private UserService userService;
    private UserRepository userRepository;

    @PostMapping(path = "users")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateParam createParam) {
        User user = userService.createUser(createParam);
        return ResponseEntity.status(201).body(user);
    }
}
