package org.eg.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.eg.tgbot.repository.StatsRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;

    public int getCountOfIncomesThatGreater(BigDecimal amount) {
        return statsRepository.getCountOfIncomesThatGreater(amount);
    }

    public int getCountOfSpendsThatGreater(Long amount) {
        return statsRepository.getCountOfSpendsThatGreater(amount);
    }

    public List<String> getStatsIncomesBetweenDates(Date from, Date to) {
        List<Map<String, Object>> rows = statsRepository.getStatsIncomesBetweenDates(from, to);
        List<String> result = new ArrayList<>();
        for (Map<String, Object> entry : rows) {
            result.add(entry.get("income") + ": " + entry.get("date"));
        }
        return result;
    }

    public List<String> getStatsSpendsBetweenDates(Date from, Date to) {
        List<Map<String, Object>> rows = statsRepository.getStatsSpendsBetweenDates(from, to);
        List<String> result = new ArrayList<>();
        for (Map<String, Object> entry : rows) {
            result.add(entry.get("spend") + ": " + entry.get("date"));
        }
        return result;
    }
}
