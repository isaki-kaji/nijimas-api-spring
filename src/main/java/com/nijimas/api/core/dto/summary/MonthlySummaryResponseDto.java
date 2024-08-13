package com.nijimas.api.core.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public class MonthlySummaryResponseDto {

    private String uid;
    private Integer year;
    private Integer month;

    @JsonProperty("expense_summary")
    private Map<String, BigDecimal> commonSummaryDto;

    @JsonProperty("subcategory_summary")
    private Map<String, BigDecimal> subCategorySummaryDto;

    @JsonProperty("daily_activity")
    private DailyActivitySummaryDto dailyActivitySummaryDto;
}
