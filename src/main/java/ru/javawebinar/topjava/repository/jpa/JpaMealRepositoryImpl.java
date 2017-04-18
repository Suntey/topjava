package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        if (meal.isNew()) {

            meal.setUser(ref);
            em.persist(meal);
            return meal;
        } else {
            if (get(meal.getId(), userId) != null) {
                meal.setUser(ref);
                return em.merge(meal);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query =  em.createQuery("DELETE FROM Meal m WHERE m.id=:id and m.user.id=:userId");
        return query.setParameter("id", id).setParameter("userId", userId).executeUpdate() != 0;

    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meal =  em.createQuery("SELECT m FROM Meal m WHERE m.id=:id and m.user.id=:userId").
                setParameter("id", id).setParameter("userId", userId).getResultList();
        return DataAccessUtils.singleResult(meal);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = em.createQuery("SELECT m FROM Meal m WHERE m.user.id=:id ORDER BY m.dateTime desc").setParameter("id", userId).getResultList();
        return meals;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = em.createQuery("SELECT m FROM Meal m WHERE m.user.id =:id AND m.dateTime BETWEEN :startdate AND :endate ORDER BY m.dateTime DESC").
                setParameter("id", userId).setParameter("startdate", startDate).setParameter("endate", endDate).getResultList();
        return meals;
    }
}