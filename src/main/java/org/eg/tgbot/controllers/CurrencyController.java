package org.eg.tgbot.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.eg.tgbot.dto.ValuteCursOnDate;
import org.eg.tgbot.service.CentralRussianBankService;
import org.eg.tgbot.service.StatsService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CentralRussianBankService centralRussianBankService;
    private final StatsService statsService;

    @GetMapping("/getCurrencies")
    @ApiOperation(value = "Получение курса всех валют на текущий день")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return centralRussianBankService.getCurrenciesFromCbr();
    }

    @GetMapping("/getCurrency/{code}")
    @ApiOperation(value = "Получение курса определенно валюты на текущий день")
    public ValuteCursOnDate getCourseForCurrency(@PathVariable String code) throws Exception {
        return centralRussianBankService.getCourseForCurrency(code);
    }

    @GetMapping("/getIncomes")
    @ApiOperation(value = "Получение количества пополнений на сумму, более указанной.")
    public int getStatsAboutIncomesThatGreater(@RequestParam(value = "amount") BigDecimal amount) {
        return statsService.getCountOfIncomesThatGreater(amount);
    }

    @GetMapping("/getSpends")
    @ApiOperation(value = "Получение списка пополнений, выше указанной суммы.")
    public int getIncomesAmountAfterDate(@RequestParam(value = "amount") Long amount) {
        return statsService.getCountOfSpendsThatGreater(amount);
    }

    @GetMapping("/getStatsBetweenDates")
    @ApiOperation(value = "Получение списка пополнений и трат в диапазоне дат.")
    public String getBetweenDates(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to) {
        StringBuilder result = new StringBuilder();
        result.append("Incomes\n");
        for (String s : statsService.getStatsIncomesBetweenDates(
                        Date.valueOf(from),
                        Date.valueOf(to)
                )) {
            result.append(s).append("\n");
        }
        result.append("Spends\n");
        for (String s : statsService.getStatsSpendsBetweenDates(
                Date.valueOf(from),
                Date.valueOf(to)
        )) {
            result.append(s).append("\n");
        }
        return result.toString();
    }
}
