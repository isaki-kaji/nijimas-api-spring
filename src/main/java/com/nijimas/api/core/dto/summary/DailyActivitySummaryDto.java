package com.nijimas.api.core.dto.summary;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DailyActivitySummaryDto {
    int day;
    int number;
    BigDecimal amount;
}
