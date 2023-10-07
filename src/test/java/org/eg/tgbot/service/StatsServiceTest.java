package org.eg.tgbot.service;

import org.eg.tgbot.repository.StatsRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private StatsRepository statsRepository;

    @BeforeEach
    public void beforeAll() {
        System.out.println(System.currentTimeMillis());
    }

    @AfterEach
    public void afterAll() {
        System.out.println(System.currentTimeMillis());
    }

    @DisplayName("Incomes_Between_Dates_test")
    @Test
    void getStatsIncomesBetweenDatesTest() {
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> outputList = statsService.getStatsIncomesBetweenDates(from, to);
        List<String> expectedList = new ArrayList<>() {
            {
                add("1300: 2023-10-04");
                add("1509: 2023-10-05");
            }
        };
        Assertions.assertLinesMatch(expectedList, outputList);
    }

    @DisplayName("Spends_Between_Dates_test")
    @Test
    void getStatsSpendsBetweenDatesTest() {
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> outputList = statsService.getStatsIncomesBetweenDates(from, to);
        List<String> expectedList = new ArrayList<>() {
            {
                add("450: 2023-10-04");
            }
        };
        Assertions.assertLinesMatch(expectedList, outputList);
    }
}