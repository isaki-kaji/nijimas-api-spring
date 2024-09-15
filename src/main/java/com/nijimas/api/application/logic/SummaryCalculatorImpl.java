package com.nijimas.api.application.logic;

import com.nijimas.api.core.dto.summary.CalculatedSummaryDto;
import com.nijimas.api.core.logic.SummaryCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 支出についての計算を行うロジッククラスです。
 * 各カテゴリの支出に対する割合を計算し、結果をマップとして返します。
 */
@Component
public class SummaryCalculatorImpl implements SummaryCalculator {

    /**
     * 各カテゴリの支出割合を計算します。
     * その後、割合が0.1%未満のカテゴリの割合を調整し、
     * 合計が100%になるように最も高い割合を持つカテゴリの割合を調整します。
     *
     * @param summary カテゴリごとの支出金額を保持するマップ
     * @return 計算された割合と支出金額を保持する {@link CalculatedSummaryDto} のマップ
     */
    public Map<String, CalculatedSummaryDto> calcSummary(Map<String, BigDecimal> summary) {
        final BigDecimal totalAmount = calcTotalAmount(summary);
        final Map<String, Double> percentages = calcPercentages(summary, totalAmount);
        if (percentages.isEmpty()) {
            return new HashMap<>();
        }

        final List<String> sortedKeys = sortKeys(percentages);
        final Map<String, Double> setMinimumPercentages = setMinimumPercent(percentages);
        final Map<String, Double> adjustedPercentages = adjustToTotal100Percent(setMinimumPercentages, sortedKeys.getFirst());

        return buildResultMap(summary, sortedKeys, adjustedPercentages);
    }

    /**
     * 支出の合計金額を計算します。
     *
     * @param summary カテゴリごとの支出金額を保持するマップ
     * @return 支出の合計金額
     */
    private BigDecimal calcTotalAmount(Map<String, BigDecimal> summary) {
        return summary.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 各カテゴリの支出金額に基づき、割合を計算します。
     * 合計支出が0であるカテゴリは除外します。
     *
     * @param summary カテゴリごとの支出金額を保持するマップ
     * @param totalAmount 支出の合計金額
     * @return 各カテゴリの支出割合を保持するマップ
     */
    private Map<String, Double> calcPercentages(Map<String, BigDecimal> summary, BigDecimal totalAmount) {
        return summary.entrySet().stream()
                .filter(s -> s.getValue().compareTo(BigDecimal.ZERO) != 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().divide(totalAmount, 3, RoundingMode.HALF_UP)
                                .doubleValue() * 100));
    }

    /**
     * 支出割合が高い順にキーを並べ替えます。
     *
     * @param percentages 各カテゴリの支出割合を保持するマップ
     * @return 支出割合が高い順に並べ替えられたキーのリスト
     */
    private List<String> sortKeys(Map<String, Double> percentages) {
        return percentages.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey).toList();
    }

    /**
     * 各カテゴリの支出割合が最低0.1%になるように調整します。
     *
     * @param percentages 各カテゴリの支出割合を保持するマップ
     * @return 調整後の支出割合を保持するマップ
     */
    private Map<String, Double> setMinimumPercent(Map<String, Double> percentages) {
        Map<String, Double> result = new LinkedHashMap<>(percentages);
        for (String key : result.keySet()) {
            if (result.get(key) < 0.1) {
                result.put(key, 0.1);
            }
        }
        return result;
    }

    /**
     * 支出割合の合計が100%になるように、最も高い割合を持つカテゴリの割合を調整します。
     *
     * @param percentages 調整された支出割合を保持するマップ
     * @param highestItem 最も高い割合を持つカテゴリのキー
     * @return 合計が100%に調整された支出割合を保持するマップ
     */
    private Map<String, Double> adjustToTotal100Percent(Map<String, Double> percentages, String highestItem) {
        double percentageTotal = percentages.values().stream().mapToDouble(Double::doubleValue).sum();

        if (percentageTotal == 100) {
            return percentages;
        }

        double adjustment = percentageTotal - 100;
        double highestValue = percentages.get(highestItem);

        if (highestValue - adjustment < 0.1) {
            adjustment = highestValue - 0.1;
        }

        percentages.put(highestItem, highestValue - adjustment);
        return percentages;
    }

    /**
     * 計算された支出割合と金額を基に、結果のマップを構築します。
     *
     * @param summary カテゴリごとの支出金額を保持するマップ
     * @param sortedKeys 支出割合が高い順に並べ替えられたキーのリスト
     * @param percentages 各カテゴリの支出割合を保持するマップ
     * @return 結果の {@link CalculatedSummaryDto} を保持するマップ
     */
    private Map<String, CalculatedSummaryDto> buildResultMap(
            Map<String, BigDecimal> summary, List<String> sortedKeys, Map<String, Double> percentages) {

        Map<String, CalculatedSummaryDto> resultMap = new LinkedHashMap<>();

        for (String key : sortedKeys) {
            resultMap.put(key, new CalculatedSummaryDto(summary.get(key), percentages.get(key)));
        }

        return resultMap;
    }
}
