package com.nijimas.api.util;

import com.nijimas.api.core.exception.common.ForbiddenException;
import com.nijimas.api.core.exception.common.InvalidUuidFormatException;

import java.util.Objects;
import java.util.UUID;

public class UserUtil {

    public static void checkUid(String uid, String ownUid) {
        if(!Objects.equals(uid, ownUid)) {
            throw new ForbiddenException();
        }
    }
}
