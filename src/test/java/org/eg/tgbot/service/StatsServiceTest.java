package org.eg.tgbot.service;

import org.eg.tgbot.repository.StatsRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
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
        List<String> expectedList = new ArrayList<>() {
            {
                add("1300: 2023-10-04");
                add("1509: 2023-10-05");
            }
        };

        List<Map<String, Object>> returnList = new ArrayList<>();
        returnList.add(new HashMap<>(){{
            put("income", 1300);
            put("date", Date.valueOf("2023-10-04"));
        }});
        returnList.add(new HashMap<>(){{
            put("income", 1509);
            put("date", Date.valueOf("2023-10-05"));
        }});

        Mockito.when(statsRepository.getStatsIncomesBetweenDates(
                Date.valueOf("2023-10-04"),
                Date.valueOf("2023-10-05")
        ))
                .thenReturn(returnList);
        List<String> actualList = statsService.getStatsIncomesBetweenDates(from, to);

        Assertions.assertLinesMatch(expectedList, actualList);
    }

    @DisplayName("Spends_Between_Dates_test")
    @Test
    void getStatsSpendsBetweenDatesTest() {
        Date from = Date.valueOf("2023-10-04");
        Date to = Date.valueOf("2023-10-05");
        List<String> expectedList = new ArrayList<>() {
            {
                add("450: 2023-10-04");
            }
        };

        List<Map<String, Object>> returnList = new ArrayList<>();
        returnList.add(new HashMap<>(){{
            put("spend", 450);
            put("date", Date.valueOf("2023-10-04"));
        }});

        Mockito.when(statsRepository.getStatsSpendsBetweenDates(
                        Date.valueOf("2023-10-04"),
                        Date.valueOf("2023-10-05")
                ))
                .thenReturn(returnList);

        List<String> actualList = statsService.getStatsSpendsBetweenDates(from, to);
        Assertions.assertLinesMatch(expectedList, actualList);
    }
}