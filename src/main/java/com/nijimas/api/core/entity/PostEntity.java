package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.util.CommonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PostEntity {

    private UUID postId;
    private String uid;
    private String mainCategory;
    private String postText;
    private String photoUrl;
    private BigDecimal expense;
    private String location;
    private String publicTypeNo;
    private OffsetDateTime createdAt;

    public PostEntity(CreatePostParam param) {
        this.postId = CommonUtil.parseUuid(param.getPostId());
        this.uid = param.getUid();
        this.mainCategory = param.getMainCategory();
        this.postText = param.getPostText();
        this.photoUrl = param.getPhotoUrl();
        this.expense = param.getExpense();
        this.location = param.getLocation();
        this.publicTypeNo = param.getPublicTypeNo();
        this.createdAt = param.getCreatedAt();
    }
}
