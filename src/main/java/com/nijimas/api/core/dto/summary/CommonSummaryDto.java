package com.nijimas.api.core.dto.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CommonSummaryDto {
    private String mainCategory;
    private BigDecimal amount;
}
