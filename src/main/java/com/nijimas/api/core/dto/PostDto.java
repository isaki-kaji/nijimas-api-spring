package com.nijimas.api.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class PostDto {
    private Long postId;
    private String uid;
    private String username;
    private String profileImageUrl;
    private String mainCategory;
    private String subCategory1;
    private String subCategory2;
    private String postText;
    private String photoUrl;
    private Integer expense;
    private String location;
    private Integer publicTypeNo;
    private OffsetDateTime createdAt;
}
