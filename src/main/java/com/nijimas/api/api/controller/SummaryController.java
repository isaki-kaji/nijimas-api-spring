package com.nijimas.api.api.controller;

import com.nijimas.api.core.repository.ExpenseSummaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * クライアント側で表示するためのユーザ自身の支出データを提供するコントローラクラスです。
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/users/me/summaries")
public class SummaryController {

    @GetMapping(path = "/{year}/{month}")
    public ResponseEntity<?> getSummaryPresentationByMonth(
            @RequestAttribute("ownUid") String ownUid,
            @PathVariable Integer year,
            @PathVariable Integer month
    ) {
        return null;
    }
}
