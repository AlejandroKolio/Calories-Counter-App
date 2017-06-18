package com.caloriescounter.app.service;

import com.caloriescounter.app.model.User;
import com.caloriescounter.app.repository.UserRepository;
import com.caloriescounter.app.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.caloriescounter.app.util.ValidationUtil.checkNotFound;
import static com.caloriescounter.app.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 21:45.
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }
}
