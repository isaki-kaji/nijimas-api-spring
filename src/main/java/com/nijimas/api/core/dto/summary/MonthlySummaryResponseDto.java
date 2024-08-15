package com.nijimas.api.core.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class MonthlySummaryResponseDto {
    private String uid;
    private Integer year;
    private Integer month;
    private Map<String, BigDecimal> expenseSummary;
    private Map<String, BigDecimal> subcategorySummary;
    private List<Integer> dailyNumbers;
    private List<BigDecimal> dailyAmounts;
}
