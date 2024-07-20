package com.nijimas.api.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class FavoriteEntity {

    private final UUID postId;
    private final String uid;
    private OffsetDateTime createdAt;

    public FavoriteEntity(UUID postId, String uid) {
        this.postId = postId;
        this.uid = uid;
    }
}
