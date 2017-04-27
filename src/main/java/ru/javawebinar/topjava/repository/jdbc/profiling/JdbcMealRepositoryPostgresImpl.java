package ru.javawebinar.topjava.repository.jdbc.profiling;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepositoryImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;

/**
 * Created by Suntey on 25.04.2017.
 */
@Repository
@Profile(Profiles.POSTGRES_DB)
public class JdbcMealRepositoryPostgresImpl extends JdbcMealRepositoryImpl<LocalDateTime> {
    public JdbcMealRepositoryPostgresImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public LocalDateTime getTime(LocalDateTime dateTime) {
        return dateTime;
    }

}
