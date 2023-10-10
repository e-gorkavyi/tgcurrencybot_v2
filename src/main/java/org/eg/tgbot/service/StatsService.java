package org.eg.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.eg.tgbot.entity.Income;
import org.eg.tgbot.entity.Spend;
import org.eg.tgbot.repository.IncomeRepository;
import org.eg.tgbot.repository.SpendRepository;
import org.springframework.stereotype.Service;
import org.eg.tgbot.repository.StatsRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    public int getCountOfIncomesThatGreater(BigDecimal amount) {
        return statsRepository.getCountOfIncomesThatGreater(amount);
    }

    public int getCountOfSpendsThatGreater(Long amount) {
        return statsRepository.getCountOfSpendsThatGreater(amount);
    }

    public List<String> getStatsIncomesBetweenDates(Date from, Date to) {
        List<String> result = new ArrayList<>();
        List<Income> incomes = incomeRepository.findByDateBetween(from, to);
        for (Income income : incomes) {
            result.add(new SimpleDateFormat("yyyy-mm-dd").format(income.getDate()) + ": " + income.getIncome());
        }
        return result;
    }

    public List<String> getStatsSpendsBetweenDates(Date from, Date to) {
        List<String> result = new ArrayList<>();
        List<Spend> spends = spendRepository.findByDateBetween(from, to);
        for (Spend spend : spends) {
            result.add(new SimpleDateFormat("yyyy-mm-dd").format(spend.getDate()) + ": " +  spend.getSpend());
        }
        return result;
    }
}
