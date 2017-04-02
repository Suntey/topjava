package ru.javawebinar.topjava.repository.mock;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    public final Table<Integer, Integer, Meal> repository = HashBasedTable.create();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal->this.save(AuthorizedUser.id(), meal));
    }

    @Override
    public synchronized Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(userId, meal.getId(), meal);
        return meal;
    }

    @Override // false if not found
    public synchronized boolean delete(int userId, int id) {
     return repository.remove(userId, id) != null;
    }


    @Override // null if not found
    public Meal get(int userId, int id) {
        return repository.get(userId, id);
    }

    @Override
    public List<Meal> getAll(int userId) {

        return MealsUtil.getSortedByDateAndTime(repository.row(Integer.valueOf(userId)).values());
    }
}

