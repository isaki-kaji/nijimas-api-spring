package com.nijimas.api.application.service.summary;

import com.nijimas.api.application.logic.SummaryCalculator;
import com.nijimas.api.core.dto.summary.CalculatedSummaryDto;
import com.nijimas.api.core.dto.summary.DailyActivitySummaryDto;
import com.nijimas.api.core.dto.summary.MonthlySummaryResponseDto;
import com.nijimas.api.core.repository.DailyActivitySummaryRepository;
import com.nijimas.api.core.repository.ExpenseSummaryRepository;
import com.nijimas.api.core.repository.SubCategorySummaryRepository;
import com.nijimas.api.core.service.SummaryPresentationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ユーザーの支出データを集計し、月次のサマリー情報を提供するサービスの実装クラスです。
 * メインカテゴリ別、サブカテゴリ別、日次のアクティビティに基づいた集計を行います。
 */
@Service
@AllArgsConstructor
public class SummaryPresentationServiceImpl implements SummaryPresentationService {

    private final ExpenseSummaryRepository expenseSummaryRepository;
    private final SubCategorySummaryRepository subCategorySummaryRepository;
    private final DailyActivitySummaryRepository dailyActivitySummaryRepository;
    private final SummaryCalculator calculator;

    /**
     * 複数のリストを一時的に保持するための内部レコードクラスです。
     * 主に日次のアクティビティデータの集計結果を保持します。
     *
     * @param numbers 日ごとのアクティビティ数のリスト
     * @param amounts 日ごとの支出金額のリスト
     */
    record Activities(List<Integer> numbers, List<BigDecimal> amounts) {
    }

    /**
     * 指定されたユーザーID、年、月に基づいて月次のサマリー情報を取得します。
     * メインカテゴリ別、サブカテゴリ別、日次のアクティビティに基づいて集計し、
     * それぞれのサマリーデータを {@link MonthlySummaryResponseDto} に格納して返します。
     *
     * @param uid   ユーザーID
     * @param year  対象年
     * @param month 対象月
     * @return 指定された年月のサマリー情報を格納した {@link MonthlySummaryResponseDto}
     */
    @Override
    public MonthlySummaryResponseDto findByMonth(String uid, Integer year, Integer month) {

        final var expenseSummary = expenseSummaryRepository.findByMonth(uid, year, month);
        final var calculatedExpenseSummary =
                expenseSummary.isEmpty() ? new HashMap<String, CalculatedSummaryDto>() : calculator.calcSummary(expenseSummary);

        final var subCategorySummary = subCategorySummaryRepository.findByMonth(uid, year, month);
        final var calculatedSubcategorySummary =
                subCategorySummary.isEmpty() ? new HashMap<String, CalculatedSummaryDto>() : calculator.calcSummary(subCategorySummary);

        final var activities = createActivityList(year, month, dailyActivitySummaryRepository.findByMonth(uid, year, month));
        return new MonthlySummaryResponseDto(
                uid, year, month, calculatedExpenseSummary, calculatedSubcategorySummary, activities.numbers, activities.amounts);
    }

    /**
     * 指定された年月の日次アクティビティのリストから、日ごとのアクティビティ数と支出金額を集計します。
     * 日付に基づいてリストを初期化し、日ごとのデータをセットします。
     *
     * @param year      対象年
     * @param month     対象月
     * @param activities 日次アクティビティのリスト
     * @return 集計結果を保持する {@link Activities} インスタンス
     */
    private Activities createActivityList(Integer year, Integer month, List<DailyActivitySummaryDto> activities) {

        final int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

        Map<Integer, DailyActivitySummaryDto> activityMap = activities.stream()
                .collect(Collectors.toMap(DailyActivitySummaryDto::getDay, Function.identity()));

        final List<Integer> numbers = new ArrayList<>(Collections.nCopies(daysInMonth, 0));
        final List<BigDecimal> amounts = new ArrayList<>(Collections.nCopies(daysInMonth, BigDecimal.ZERO));

        for (int i = 1; i <= daysInMonth; i++) {
            DailyActivitySummaryDto dto = activityMap.get(i);
            if (dto != null) {
                numbers.set(i - 1, dto.getNumber());
                amounts.set(i - 1, dto.getAmount());
            }
        }

        return new Activities(numbers, amounts);
    }
}

