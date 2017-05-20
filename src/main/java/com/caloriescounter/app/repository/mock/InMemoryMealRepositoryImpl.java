package com.caloriescounter.app.repository.mock;

import com.caloriescounter.app.model.Meal;
import com.caloriescounter.app.repository.MealRepository;
import com.caloriescounter.app.util.DateTimeUtil;
import com.caloriescounter.app.util.MealsUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.caloriescounter.app.repository.mock.InMemoryUserRepositoryImpl.USER_ID;
import static com.caloriescounter.app.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;

/**
 * Created by Aleksandr_Shakhov on 13.05.17 14:46.
 */

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Comparator<Meal> MEAL_COMPARATOR = Comparator.comparing(Meal::getDateTime).reversed();

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));

        save(new Meal(LocalDateTime.of(2017, Month.MAY, 13, 15, 0), "Admin lunch", 510), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2017, Month.MAY, 13, 16, 30), "Admin dinner", 500), ADMIN_ID);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);
        if(meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else if(get(meal.getId(), userId) == null){
            return null;
        }
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream(userId)
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

    private Stream<Meal> getAllAsStream(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? Stream.empty() : meals.values().stream().sorted(MEAL_COMPARATOR);
    }
}
