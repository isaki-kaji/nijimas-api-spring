package com.nijimas.api.core.dto.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MonthlySummaryResponseDto {

    private String uid;
    private Integer year;
    private Integer month;

    @JsonProperty("expense_summary")
    private ExpenseSummaryDto expenseSummaryDto;

    @JsonProperty("subcategory_summary")
    private SubCategorySummaryDto subCategorySummaryDto;

    @JsonProperty("daily_activity")
    private DailyActivitySummaryDto dailyActivitySummaryDto;
}
