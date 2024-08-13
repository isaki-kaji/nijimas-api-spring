package com.nijimas.api.core.dto.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ExpenseSummaryDto {
    Map<String, BigDecimal> items;
}
