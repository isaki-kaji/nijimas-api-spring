package com.nijimas.api.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthlyExpenseSummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private String mainCategory;
    private Integer totalExpense;
}
