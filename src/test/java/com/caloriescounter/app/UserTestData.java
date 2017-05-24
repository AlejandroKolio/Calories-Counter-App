package com.caloriescounter.app;

import com.caloriescounter.app.model.Role;
import com.caloriescounter.app.model.User;

/**
 * Created by Aleksandr_Shakhov on 24.05.17 21:57.
 */


public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
}
