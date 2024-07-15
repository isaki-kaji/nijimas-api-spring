package com.nijimas.api.core.model;

import com.nijimas.api.application.post.CreateParam;
import com.nijimas.api.util.CommonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Post {

    private UUID postId;
    private String uid;
    private String mainCategory;
    private String postText;
    private String photoUrl;
    private Integer expense;
    private String location;
    private String publicTypeNo;
    private OffsetDateTime createdAt;

    public Post(CreateParam param) {
        this.postId = CommonUtil.parseUuid(param.getPostId());
        this.uid = param.getUid();
        this.mainCategory = param.getUid();
        this.postText = param.getPostText();
        this.photoUrl = param.getPhotoUrl();
        this.expense = param.getExpense();
        this.location = param.getLocation();
        this.publicTypeNo = param.getPublicTypeNo();
    }
}
