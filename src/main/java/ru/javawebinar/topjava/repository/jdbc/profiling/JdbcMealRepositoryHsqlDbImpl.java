package ru.javawebinar.topjava.repository.jdbc.profiling;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepositoryImpl;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Created by Suntey on 25.04.2017.
 */
@Repository
@Profile(Profiles.HSQL_DB)
public class JdbcMealRepositoryHsqlDbImpl extends JdbcMealRepositoryImpl {
    public JdbcMealRepositoryHsqlDbImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public Timestamp getTime(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }


}
