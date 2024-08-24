package com.nijimas.api.core.dto.summary;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class CalculatedSummaryDto {
    private final BigDecimal amount;
    private final BigDecimal percentage;

    public CalculatedSummaryDto(BigDecimal amount, double percentage) {
        this.amount = amount;
        this.percentage = BigDecimal.valueOf(percentage).setScale(1, RoundingMode.HALF_UP);
    }
}
