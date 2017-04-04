package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suntey on 01.04.2017.
 */
public class UsersUtil {
    public static List<User> getSortedByName(Collection<User> users){
        return users.stream().sorted(Comparator.comparing(User::getName).thenComparing(Comparator.comparing(User::getEmail))).collect(Collectors.toList());
    }

    public static User findUserByEmail(Collection<User> users, String email){
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
