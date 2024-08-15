package com.nijimas.api.util;

import com.nijimas.api.core.dto.summary.CommonSummaryDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryUtil {

    private RepositoryUtil() {
        throw new AssertionError();
    }

    public static Map<String, BigDecimal> toSummaryMap(List<CommonSummaryDto> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        CommonSummaryDto::getCategory,
                        CommonSummaryDto::getAmount
                ));
    }
}
