package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExpenseSummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private String mainCategory;
    private BigDecimal amount;

    public ExpenseSummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.mainCategory = param.getMainCategory();
        this.amount = param.getExpense();
    }

    public ExpenseSummaryEntity addExpense(BigDecimal expense) {
        BigDecimal newAmount = this.amount.add(expense).min(CommonConstants.MAX_EXPENSE);
        return new ExpenseSummaryEntity(
                uid, year, month, mainCategory, newAmount);
    }
}
