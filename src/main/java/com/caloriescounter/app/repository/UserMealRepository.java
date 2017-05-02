package com.caloriescounter.app.repository;

import com.caloriescounter.app.model.Meal;

import java.util.Collection;

/**
 * Created by Aleksandr_Shakhov on 02.05.17 19:54.
 */


public interface UserMealRepository {
    Meal save(Meal meal);
    void delete(int id);
    Meal get(int id);
    Collection<Meal> getAll();
}
