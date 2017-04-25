package ru.javawebinar.topjava.service.jdbcprofile;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Suntey on 25.04.2017.
 */
@ActiveProfiles(Profiles.JDBC)
public class UserServiceJdbcTest extends UserServiceTest {
}
