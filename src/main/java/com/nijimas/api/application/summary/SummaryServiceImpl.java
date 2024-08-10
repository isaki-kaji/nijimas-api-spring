package com.nijimas.api.application.summary;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;
import com.nijimas.api.core.repository.MonthlyExpenseSummaryRepository;
import com.nijimas.api.core.service.SummaryService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 支出の集計を行うサービスクラスです。
 * 集計はユーザがポストを投稿した際に行われます。
 */
@Service
@AllArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    final private MonthlyExpenseSummaryRepository repository;

    /**
     * ユーザが投稿したデータに基づき、支出の集計を非同期で実行します。
     *
     * @param param　{@link CreatePostParam} オブジェクト
     */
    @Override
    @Async
    public void execute(CreatePostParam param) {
        calcMonthlySummary(param);
    }

    /**
     * 月次支出サマリーを計算し、必要に応じて更新または新規作成します。
     *
     * @param param {@link CreatePostParam} オブジェクト
     */
    private void calcMonthlySummary(CreatePostParam param) {
        var summary = new MonthlyExpenseSummaryEntity(param);
        repository.findOne(summary).ifPresentOrElse(
                s -> repository.update(summary.addExpense(param.getExpense())),
                () -> repository.save(summary)
        );
    }
}
