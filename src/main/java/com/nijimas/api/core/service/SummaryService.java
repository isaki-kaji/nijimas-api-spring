package com.nijimas.api.core.service;

import com.nijimas.api.application.post.CreatePostParam;

public interface SummaryService {

    void calcMonthlySummary(CreatePostParam param);
}
