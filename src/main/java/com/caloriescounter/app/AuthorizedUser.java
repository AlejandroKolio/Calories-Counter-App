package com.caloriescounter.app;

import com.caloriescounter.app.model.Role;

import java.util.Collections;
import java.util.Set;

import static com.caloriescounter.app.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 21:18.
 */


public class AuthorizedUser {

    private static int id = 1;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}
