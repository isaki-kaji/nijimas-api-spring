package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
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

    public MonthlyExpenseSummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.mainCategory = param.getMainCategory();
        this.totalExpense = param.getExpense();
    }

    public MonthlyExpenseSummaryEntity addExpense(Integer otherExpense) {
        Integer newExpense = Math.min(
                this.totalExpense + otherExpense, CommonConstants.MAX_EXPENSE);
        return new MonthlyExpenseSummaryEntity(
                uid, year, month, mainCategory, newExpense);
    }
}
