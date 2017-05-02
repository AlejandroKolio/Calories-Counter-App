package com.caloriescounter.app.repository;

import com.caloriescounter.app.model.Meal;
import com.caloriescounter.app.util.UserMealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aleksandr_Shakhov on 02.05.17 19:57.
 */


public class InMemoryUserMealRepository implements UserMealRepository {

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if(meal.isNew()) {
            meal.setId(counter.getAndIncrement());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}
