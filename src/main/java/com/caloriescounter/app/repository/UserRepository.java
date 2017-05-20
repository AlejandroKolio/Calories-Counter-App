package com.caloriescounter.app.repository;

import com.caloriescounter.app.model.User;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 21:22.
 */


public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
