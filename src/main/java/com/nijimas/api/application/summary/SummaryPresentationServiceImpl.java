package com.nijimas.api.application.summary;

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

@Service
@AllArgsConstructor
public class SummaryPresentationServiceImpl implements SummaryPresentationService {

    private final ExpenseSummaryRepository expenseSummaryRepository;
    private final SubCategorySummaryRepository subCategorySummaryRepository;
    private final DailyActivitySummaryRepository dailyActivitySummaryRepository;

    // createActivityListが2つのListを返すために一時的に利用されるレコード
    record Activities(List<Integer> numbers, List<BigDecimal> amounts) {
    }

    public MonthlySummaryResponseDto findByMonth(String uid, Integer year, Integer month) {
        final var expenseSummary = expenseSummaryRepository.findByMonth(uid, year, month);
        final var subCategorySummary = subCategorySummaryRepository.findByMonth(uid, year, month);
        final var activities = createActivityList(year, month, dailyActivitySummaryRepository.findByMonth(uid, year, month));
        return new MonthlySummaryResponseDto(
                uid, year, month, expenseSummary, subCategorySummary, activities.numbers, activities.amounts);
    }

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
