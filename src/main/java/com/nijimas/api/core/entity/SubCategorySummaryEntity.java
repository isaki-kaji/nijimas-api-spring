package com.nijimas.api.core.entity;

import com.nijimas.api.application.service.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SubCategorySummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    @Setter
    private String subCategory;
    private BigDecimal amount;

    public SubCategorySummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.amount = param.getExpense();
    }

    public SubCategorySummaryEntity addExpense(BigDecimal expense) {
        BigDecimal newAmount = this.amount.add(expense).min(CommonConstants.MAX_EXPENSE);
        return new SubCategorySummaryEntity(
                uid, year, month, subCategory, newAmount);
    }
}
