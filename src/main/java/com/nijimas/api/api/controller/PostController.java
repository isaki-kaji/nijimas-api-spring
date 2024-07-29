package com.nijimas.api.api.controller;

import com.nijimas.api.application.post.CreateParam;
import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> registerPost(
            @RequestBody @Valid CreateParam param,
            @RequestAttribute("ownUid") String ownUid) {
        param.setUid(ownUid);
        postService.registerPost(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "uid")
    public ResponseEntity<?> getPostsByUid(@RequestParam String uid) {
        List<PostDto> posts = postService.findByUid(uid);
        return ResponseEntity.ok(posts);
    }
}