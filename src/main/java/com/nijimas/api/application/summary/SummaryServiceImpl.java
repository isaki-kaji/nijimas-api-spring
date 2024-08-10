package com.nijimas.api.application.summary;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;
import com.nijimas.api.core.repository.MonthlyExpenseSummaryRepository;
import com.nijimas.api.core.service.SummaryService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    final private MonthlyExpenseSummaryRepository repository;

    @Override
    @Async
    public void execute(CreatePostParam param) {
        calcMonthlySummary(param);
    }

    private void calcMonthlySummary(CreatePostParam param) {
        var summary = new MonthlyExpenseSummaryEntity(param);
        repository.findOne(summary).ifPresentOrElse(
                s -> repository.update(summary.addExpense(param.getExpense())),
                () -> repository.save(summary)
        );
    }
}
