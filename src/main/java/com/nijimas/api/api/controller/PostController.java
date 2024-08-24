package com.nijimas.api.api.controller;

import com.nijimas.api.application.service.post.CreatePostParam;
import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.service.PostService;
import com.nijimas.api.util.ControllerUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(path = "/posts")
    public ResponseEntity<?> registerPost(
            @RequestBody @Valid CreatePostParam param,
            @RequestAttribute("ownUid") String ownUid) {
        param.setUid(ownUid);
        postService.registerPost(param);
        URI location = ControllerUtil.createLocation(param.getPostId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/me/posts")
    public ResponseEntity<?> getOwnPosts(
            @RequestAttribute("ownUid") String ownUid) {
        List<PostDto> posts = postService.findByUid(ownUid);
        return ResponseEntity.ok(posts);
    }

    @GetMapping(path = "/users/{uid}/posts")
    public ResponseEntity<?> getPostsByUid(@PathVariable("uid") String uid) {
        List<PostDto> posts = postService.findByUid(uid);
        return ResponseEntity.ok(posts);
    }
}
