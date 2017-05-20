package com.caloriescounter.app.repository;

import com.caloriescounter.app.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 13.05.17 14:43.
 */


public interface MealRepository {

    Meal save(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
