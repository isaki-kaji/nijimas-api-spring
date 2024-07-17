package com.nijimas.api.core.entity;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.application.user.UpdateParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"uid"})
public class UserEntity {
    private String uid;
    private String username;
    private String selfIntro;
    private String profileImageUrl;
    private String bannerImageUrl;
    private String countryCode;
    private OffsetDateTime createdAt;

    public UserEntity(CreateParam param) {
        this.uid = param.getUid();
        this.username = param.getUsername();
        this.countryCode = param.getCountryCode();
    }

    public UserEntity(UpdateParam param) {
        this.uid = param.getUid();
        this.username = param.getUsername();
        this.countryCode = param.getCountryCode();
        this.selfIntro = param.getSelfIntro();
        this.profileImageUrl = param.getProfileImageUrl();
    }
}
