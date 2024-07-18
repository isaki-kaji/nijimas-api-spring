package com.nijimas.api.util;

import com.nijimas.api.core.exception.common.InvalidUuidFormatException;

import java.util.UUID;

public class CommonUtil {

    public static UUID parseUuid(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidFormatException();
        }
    }
}
