package com.nijimas.api.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class PostDto {
    private UUID postId;
    private String uid;
    private String username;
    private String profileImageUrl;
    private String mainCategory;
    private String subCategory1;
    private String subCategory2;
    private String postText;
    private List<String> photoUrl;
    private Integer expense;
    private String location;
    private String publicTypeNo;
    private OffsetDateTime createdAt;
    private Boolean isFavorite;
}
