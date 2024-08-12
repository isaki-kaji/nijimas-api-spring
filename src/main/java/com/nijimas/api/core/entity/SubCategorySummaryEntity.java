package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubCategorySummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private String subCategory;
    private Integer amount;

    public SubCategorySummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.amount = param.getExpense();
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public SubCategorySummaryEntity addExpense(Integer expense) {
        Integer newAmount = Math.min(
                this.amount + expense, CommonConstants.MAX_EXPENSE);
        return new SubCategorySummaryEntity(
                uid, year, month, subCategory, newAmount);
    }
}
