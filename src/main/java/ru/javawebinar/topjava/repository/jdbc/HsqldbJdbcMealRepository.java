package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Repository
@Profile(Profiles.HSQL_DB)
public class HsqldbJdbcMealRepository extends JdbcMealRepository {

    @Autowired
    public HsqldbJdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected Object convertToActualDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
