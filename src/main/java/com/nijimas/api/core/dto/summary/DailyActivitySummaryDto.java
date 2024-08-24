package com.nijimas.api.core.dto.summary;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DailyActivitySummaryDto {
    private int day;
    private int number;
    private BigDecimal amount;
}
