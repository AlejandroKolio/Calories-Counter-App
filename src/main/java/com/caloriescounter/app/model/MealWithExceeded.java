package com.caloriescounter.app.model;

import java.time.LocalDateTime;

/**
 * This is the class described Model of User Meal with
 * exceeded calories.
 *
 * @author Aleksandr Shakhov
 * @version 1.0
 */


public class MealWithExceeded {

    private Integer id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean exceeded;

    /*public MealWithExceeded(LocalDateTime dateTime, String description, int calories, boolean exceeded) {
        this(null, dateTime, description, calories, exceeded);
    }*/

    public MealWithExceeded(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceeded) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceeded = exceeded;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceeded() {
        return exceeded;
    }

    @Override
    public String toString() {
        return "MealWithExceeded{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceeded=" + exceeded +
                '}';
    }
}
