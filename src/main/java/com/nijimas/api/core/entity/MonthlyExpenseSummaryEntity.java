package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MonthlyExpenseSummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private String mainCategory;
    private Integer totalExpense;

    public MonthlyExpenseSummaryEntity(CreatePostParam param) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        this.uid = param.getUid();
        this.year = currentDateTime.getYear();
        this.month = currentDateTime.getMonthValue();
        this.mainCategory = param.getMainCategory();
        this.totalExpense = param.getExpense();
    }

    public MonthlyExpenseSummaryEntity addExpense(Integer expense) {
        return new MonthlyExpenseSummaryEntity(
                this.uid,
                this.year,
                this.month,
                this.mainCategory,
                this.totalExpense + expense
        );
    }
}
