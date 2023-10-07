package org.eg.tgbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class StatsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int getCountOfIncomesThatGreater(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "select count(*) from incomes where income > :amount",
                parameters,
                new StatsRowMapper()
        );
    }

    public int getCountOfSpendsThatGreater(Long amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "select count(*) from spend where spend > :amount",
                parameters,
                new StatsRowMapper()
        );
    }

    public List<Map<String, Object>> getStatsIncomesBetweenDates(Date from, Date to) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", from);
        parameters.put("to", to);
        return namedParameterJdbcTemplate.queryForList(
                "SELECT INCOME, DATE FROM public.incomes where date >= :from and date <= :to",
                parameters
        );
    }

    public List<Map<String, Object>> getStatsSpendsBetweenDates(Date from, Date to) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", from);
        parameters.put("to", to);
        return namedParameterJdbcTemplate.queryForList(
                "SELECT SPEND, DATE FROM public.spend where date >= :from and date <= :to",
                parameters
        );
    }

    private static final class StatsRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("COUNT");
        }
    }

    private static final class StatsDatesRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return String.valueOf(rs.getInt("INCOME")) + " " + String.valueOf(rs.getString("DATE"));
        }
    }
}
