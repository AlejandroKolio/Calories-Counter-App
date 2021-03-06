package com.caloriescounter.app.web.user;

import com.caloriescounter.app.model.User;
import com.caloriescounter.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.caloriescounter.app.util.ValidationUtil.checkIdConsistent;
import static com.caloriescounter.app.util.ValidationUtil.checkNew;

/**
 * Created by Aleksandr_Shakhov on 13.05.17 14:22.
 */


public class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserService service;

    public AbstractUserController(UserService service) {
        this.service = service;
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {}", user);
        checkIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
