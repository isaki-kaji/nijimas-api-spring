package com.nijimas.api.core.service;

import com.nijimas.api.core.dto.summary.MonthlySummaryResponseDto;

public interface SummaryPresentationService {

    MonthlySummaryResponseDto findByMonth(String uid, Integer year, Integer month);
}
