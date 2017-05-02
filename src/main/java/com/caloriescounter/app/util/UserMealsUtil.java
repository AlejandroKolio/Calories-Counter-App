package com.caloriescounter.app.util;

import com.caloriescounter.app.model.Meal;
import com.caloriescounter.app.model.MealWithExceeded;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr_Shakhov on 26.04.17 22:09.
 */


public class UserMealsUtil {

    public static final List<Meal> MEAL_LIST = Arrays.asList(
            new Meal(LocalDateTime.of(2017, Month.APRIL, 26, 9, 0, 0), "Breakfast", 500),
            new Meal(LocalDateTime.of(2017, Month.APRIL, 26, 14, 0, 0), "Dinner", 1000),
            new Meal(LocalDateTime.of(2017, Month.APRIL, 26, 22, 32, 0), "Supper", 500),
            new Meal(LocalDateTime.of(2017, Month.APRIL, 27, 9, 0, 0), "Breakfast", 500),
            new Meal(LocalDateTime.of(2017, Month.APRIL, 27, 14, 0, 0), "Dinner", 1500),
            new Meal(LocalDateTime.of(2017, Month.APRIL, 27, 22, 32, 0), "Supper", 500)
    );

    public static void main(String[] args) {
        /*List<MealWithExceeded> filtratedMealsWithExceeded = getFiltratedMealsWithExceeded(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filtratedMealsWithExceeded.forEach(System.out::println);*/
    }

    public static List<MealWithExceeded> getNonFiltratedMealsWithExceeded(Collection<Meal> meals, int caloriesPerDay) {
        return getFiltratedMealsWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<MealWithExceeded> getFiltratedMealsWithExceeded(Collection<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),
                        Collectors.summingInt(Meal::getCalories)));

        return mealList.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static MealWithExceeded createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceeded(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
}

