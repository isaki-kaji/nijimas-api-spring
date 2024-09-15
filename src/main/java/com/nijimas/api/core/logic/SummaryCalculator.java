package com.nijimas.api.core.logic;

import com.nijimas.api.core.dto.summary.CalculatedSummaryDto;

import java.math.BigDecimal;
import java.util.Map;

public interface SummaryCalculator {
     Map<String, CalculatedSummaryDto> calcSummary(Map<String, BigDecimal> summary);
}
