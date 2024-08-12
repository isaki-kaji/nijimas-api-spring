package com.nijimas.api.core.entity;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyActivitySummaryEntity {
    private String uid;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer number;
    private Integer amount;

    public DailyActivitySummaryEntity(CreatePostParam param) {
        this.uid = param.getUid();
        this.year = param.getCreatedAt().getYear();
        this.month = param.getCreatedAt().getMonthValue();
        this.day = param.getCreatedAt().getDayOfMonth();
        this.number = 1;
        this.amount = param.getExpense();
    }

    public DailyActivitySummaryEntity update(Integer expense) {
        Integer newAmount = Math.min(
                this.amount + expense, CommonConstants.MAX_EXPENSE);
        return new DailyActivitySummaryEntity(
                uid, year, month, day, number + 1 ,newAmount);
    }
}
