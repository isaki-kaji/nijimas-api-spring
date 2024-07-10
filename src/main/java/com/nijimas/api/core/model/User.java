package com.nijimas.api.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"uid"})
public class User {
    private String uid;
    private String username;
    private String selfIntro;
    private String profileImageUrl;
    private String bannerImageUrl;
    private String countryCode;
    private OffsetDateTime createdAt;

    public User(String uid ,String username, String countryCode) {
        this.uid = uid;
        this.username = username;
        this.countryCode = countryCode;
    }
}
