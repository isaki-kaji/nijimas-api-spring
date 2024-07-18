package com.nijimas.api.controller;

import com.nijimas.api.application.post.CreateParam;
import com.nijimas.api.core.service.PostService;
import com.nijimas.api.util.UserUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> registerPost(
            @RequestBody @Valid CreateParam createParam,
            @RequestAttribute("ownUid") String ownUid) {
        UserUtil.checkUid(createParam.getUid(), ownUid);
        postService.registerPost(createParam, ownUid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
