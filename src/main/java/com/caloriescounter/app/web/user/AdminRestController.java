package com.caloriescounter.app.web.user;

import com.caloriescounter.app.model.User;
import com.caloriescounter.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 11.05.17 20:47.
 */

@Controller
public class AdminRestController extends AbstractUserController {

    @Autowired
    public AdminRestController(UserService service) {
        super(service);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}