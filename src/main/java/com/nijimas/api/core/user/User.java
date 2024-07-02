package com.nijimas.api.core.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private ZonedDateTime createdAt;

    public User(String uid ,String username) {
        this.uid = uid;
        this.username = username;
    }
}
