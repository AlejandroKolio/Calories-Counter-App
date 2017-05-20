package com.caloriescounter.app.service;

import com.caloriescounter.app.model.User;
import com.caloriescounter.app.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 21:31.
 */


public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user) throws NotFoundException;
}
