package com.nijimas.api.api.controller;

import com.nijimas.api.core.service.SummaryPresentationService;
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
    private final SummaryPresentationService service;
    //未来に例外を返す

    @GetMapping(path = "/{year}/{month}")
    public ResponseEntity<?> getSummaryPresentationByMonth(
            @RequestAttribute("ownUid") String ownUid,
            @PathVariable Integer year,
            @PathVariable Integer month
    ) {
        return ResponseEntity.ok(service.findByMonth(ownUid, year, month));
    }
}
