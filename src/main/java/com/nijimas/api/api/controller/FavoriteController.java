package com.nijimas.api.api.controller;

import com.nijimas.api.application.favorite.ToggleParam;
import com.nijimas.api.core.constant.FavoriteStatusConstants;
import com.nijimas.api.core.exception.ApiErrorResponse;
import com.nijimas.api.core.exception.post.PostNotFoundException;
import com.nijimas.api.core.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<?> toggleFavorite(
            @RequestBody @Valid ToggleParam param,
            @RequestAttribute("ownUid") String ownUid) {
        param.setUid(ownUid);
        try {
            FavoriteStatusConstants status = favoriteService.toggleFavorite(param);
            if (status == FavoriteStatusConstants.CREATED) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorResponse(e));
        }
    }
}
