package com.nijimas.api.util;

import com.nijimas.api.core.exception.common.InvalidUuidFormatException;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;

import java.util.UUID;

public class CommonUtil {

    // インスタンス化できないようにしている
    private CommonUtil() {
        throw new AssertionError();
    }

    private static final TimeBasedEpochGenerator GENERATOR;
    static {
        GENERATOR = Generators.timeBasedEpochGenerator();
    }

    public static UUID generateUuid() {
        return GENERATOR.generate();
    }

    public static UUID parseUuid(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidFormatException();
        }
    }
}
