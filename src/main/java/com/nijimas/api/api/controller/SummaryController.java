package com.nijimas.api.api.controller;

import com.nijimas.api.core.constant.MessageConstants;
import com.nijimas.api.core.exception.ApiErrorResponse;
import com.nijimas.api.core.service.SummaryPresentationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

/**
 * クライアント側で表示するためのユーザ自身の支出データを提供するコントローラクラスです。
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/users/me/summaries")
public class SummaryController {
    private final SummaryPresentationService service;

    @GetMapping(path = "/{year}/{month}")
    public ResponseEntity<?> getSummaryPresentationByMonth(
            @RequestAttribute("ownUid") String ownUid,
            @PathVariable int year,
            @PathVariable int month
    ) {
        if (year < 2024) {
            return ResponseEntity.badRequest().body(new ApiErrorResponse(MessageConstants.YEAR_OLD_INVALID));
        }

        final var yearMonth = YearMonth.of(year, month);
        final var currentYearMonth = YearMonth.now();

        if (yearMonth.isAfter(currentYearMonth)) {
            return ResponseEntity.badRequest().body(new ApiErrorResponse(MessageConstants.YEAR_MONTH_INVALID));
        }
        return ResponseEntity.ok(service.findByMonth(ownUid, year, month));
    }
}
