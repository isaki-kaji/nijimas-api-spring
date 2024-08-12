package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthlySummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private String mainCategory;
    private Integer amount;

    public MonthlySummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.mainCategory = param.getMainCategory();
        this.amount = param.getExpense();
    }

    public MonthlySummaryEntity addExpense(Integer expense) {
        Integer newAmount = Math.min(
                this.amount + expense, CommonConstants.MAX_EXPENSE);
        return new MonthlySummaryEntity(
                uid, year, month, mainCategory, newAmount);
    }
}
