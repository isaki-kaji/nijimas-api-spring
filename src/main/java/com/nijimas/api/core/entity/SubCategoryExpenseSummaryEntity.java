package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubCategoryExpenseSummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private String subCategory;
    private Integer totalExpense;

    public SubCategoryExpenseSummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.totalExpense = param.getExpense();
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public SubCategoryExpenseSummaryEntity addExpense(Integer otherExpense) {
        Integer newExpense = Math.min(
                this.totalExpense + otherExpense, CommonConstants.MAX_EXPENSE);
        return new SubCategoryExpenseSummaryEntity(
                uid, year, month, subCategory, newExpense);
    }
}
