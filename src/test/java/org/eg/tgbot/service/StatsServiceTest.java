package org.eg.tgbot.service;

import org.eg.tgbot.entity.Income;
import org.eg.tgbot.entity.Spend;
import org.eg.tgbot.repository.IncomeRepository;
import org.eg.tgbot.repository.SpendRepository;
import org.eg.tgbot.repository.StatsRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private SpendRepository spendRepository;

    @BeforeEach
    public void beforeAll() {
        System.out.println(System.currentTimeMillis());
    }

    @AfterEach
    public void afterAll() {
        System.out.println(System.currentTimeMillis());
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    @DisplayName("Incomes_Between_Dates_test")
    @Test
    void getStatsIncomesBetweenDatesTest() throws ParseException {
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> expectedList = new ArrayList<>() {
            {
                add("2023-10-04: 1300");
                add("2023-10-05: 1509");
            }
        };

        List<Income> returnList = new ArrayList<>();
        returnList.add(new Income(){{
            setIncome(BigDecimal.valueOf(1300));
            setDate(dateFormat.parse("2023-10-04"));
        }});
        returnList.add(new Income(){{
            setIncome(BigDecimal.valueOf(1509));
            setDate(dateFormat.parse("2023-10-05"));
        }});

        Mockito.when(incomeRepository.findByDateBetween(
                Date.valueOf("2023-10-04"),
                Date.valueOf("2023-10-05")
        ))
                .thenReturn(returnList);

        List<String> actualList = statsService.getStatsIncomesBetweenDates(from, to);

        Assertions.assertLinesMatch(expectedList, actualList);
    }

    @DisplayName("Spends_Between_Dates_test")
    @Test
    void getStatsSpendsBetweenDatesTest() throws ParseException {
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> expectedList = new ArrayList<>() {
            {
                add("2023-10-04: 450");
            }
        };

        List<Spend> returnList = new ArrayList<>();
        returnList.add(new Spend(){{
            setSpend(BigDecimal.valueOf(450));
            setDate(dateFormat.parse("2023-10-04"));
        }});

        Mockito.when(spendRepository.findByDateBetween(
                        Date.valueOf("2023-10-04"),
                        Date.valueOf("2023-10-05")
                ))
                .thenReturn(returnList);

        List<String> actualList = statsService.getStatsSpendsBetweenDates(from, to);
        Assertions.assertLinesMatch(expectedList, actualList);
    }
}