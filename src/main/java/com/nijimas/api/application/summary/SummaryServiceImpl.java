package com.nijimas.api.application.summary;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.core.constant.CommonConstants;
import com.nijimas.api.core.entity.DailyActivitySummaryEntity;
import com.nijimas.api.core.entity.MonthlySummaryEntity;
import com.nijimas.api.core.entity.SubCategorySummaryEntity;
import com.nijimas.api.core.repository.DailyActivitySummaryRepository;
import com.nijimas.api.core.repository.MonthlyExpenseSummaryRepository;
import com.nijimas.api.core.repository.SubCategoryExpenseSummaryRepository;
import com.nijimas.api.core.service.SummaryService;
import com.nijimas.api.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 支出の集計を行うサービスクラスです。
 * 集計はユーザがポストを投稿した際に行われます。
 */
@Service
@AllArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    final private MonthlyExpenseSummaryRepository monthlySummaryRepository;
    final private SubCategoryExpenseSummaryRepository subCategorySummaryRepository;
    final private DailyActivitySummaryRepository dailyActivitySummaryRepository;

    /**
     * ユーザが投稿したデータに基づき、支出の集計を非同期で実行します。
     *
     * @param param 　{@link CreatePostParam} オブジェクト
     */
    @Override
    @Async
    public void execute(CreatePostParam param) {
        calcMonthlySummary(param);
        calcSubCategorySummary(param);
        calcDailyActivitySummary(param);
    }

    /**
     * 費目ごとの月次支出を計算し、集計結果を更新または新規作成します。
     *
     * @param param {@link CreatePostParam} オブジェクト
     */
    private void calcMonthlySummary(CreatePostParam param) {
        var summary = new MonthlySummaryEntity(param);
        monthlySummaryRepository.findOne(summary).ifPresentOrElse(
                s -> {
                    if (s.getAmount().compareTo(CommonConstants.MAX_EXPENSE) >= 0) {
                        return;
                    }
                    monthlySummaryRepository.update(s.addExpense(param.getExpense()));
                },
                () -> monthlySummaryRepository.save(summary)
        );
    }

    /**
     * サブカテゴリごとの月次支出を計算し、集計結果を更新または新規作成します。
     *
     * @param param {@link CreatePostParam} オブジェクト
     */
    private void calcSubCategorySummary(CreatePostParam param) {
        var subCategories = Stream.of(param.getSubCategory1(), param.getSubCategory2())
                .filter(Objects::nonNull)
                .toList();

        for (String subCategory : subCategories) {
            var summary = new SubCategorySummaryEntity(param);
            summary.setSubCategory(subCategory);
            subCategorySummaryRepository.findOne(summary).ifPresentOrElse(
                    s -> {
                        if (s.getAmount().equals(CommonConstants.MAX_EXPENSE)) {
                            return;
                        }
                        subCategorySummaryRepository.update(s.addExpense(param.getExpense()));
                    },
                    () -> subCategorySummaryRepository.save(summary)
            );
        }
    }

    /**
     * 日別の投稿数と支出を計算し、集計結果を更新または新規作成します。
     *
     * @param param {@link CreatePostParam} オブジェクト
     */
    private void calcDailyActivitySummary(CreatePostParam param){
        var summary = new DailyActivitySummaryEntity(param);
        dailyActivitySummaryRepository.findOne(summary).ifPresentOrElse(
                s -> {
                    if (s.getAmount().compareTo(CommonConstants.MAX_EXPENSE) >= 0) {
                        return;
                    }
                    dailyActivitySummaryRepository.update(s.update(param.getExpense()));
                },
                () -> dailyActivitySummaryRepository.save(summary)
        );
    }
}
