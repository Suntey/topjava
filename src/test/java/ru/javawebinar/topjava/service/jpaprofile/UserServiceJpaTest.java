package ru.javawebinar.topjava.service.jpaprofile;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Suntey on 25.04.2017.
 */
@ActiveProfiles(Profiles.JPA)
public class UserServiceJpaTest extends UserServiceTest {
}
