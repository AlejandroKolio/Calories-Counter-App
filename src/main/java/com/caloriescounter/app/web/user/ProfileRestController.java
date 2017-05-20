package com.caloriescounter.app.web.user;

import com.caloriescounter.app.AuthorizedUser;
import com.caloriescounter.app.model.User;
import com.caloriescounter.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Aleksandr_Shakhov on 13.05.17 14:38.
 */

@Controller
public class ProfileRestController extends AbstractUserController {

    @Autowired
    public ProfileRestController(UserService service) {
        super(service);
    }

    @Override
    public User get(int id) {
        return super.get(AuthorizedUser.id());
    }

    @Override
    public void delete(int id) {
        super.delete(AuthorizedUser.id());
    }

    @Override
    public void update(User user, int id) {
        super.update(user, AuthorizedUser.id());
    }
}
