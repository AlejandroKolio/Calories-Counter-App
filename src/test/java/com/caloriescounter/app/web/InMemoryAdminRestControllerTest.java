package com.caloriescounter.app.web;

import com.caloriescounter.app.UserTestData;
import com.caloriescounter.app.model.User;
import com.caloriescounter.app.repository.UserRepository;
import com.caloriescounter.app.util.exception.NotFoundException;
import com.caloriescounter.app.web.user.AdminRestController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static com.caloriescounter.app.UserTestData.USER;
import static com.caloriescounter.app.UserTestData.ADMIN;

/**
 * Created by Aleksandr_Shakhov on 24.05.17 22:10.
 */

@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerTest {

    @Autowired
    private static AdminRestController adminRestController;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.getAll().forEach(user -> repository.delete(user.getId()));
        repository.save(USER);
        repository.save(ADMIN);
    }

    @Test
    public void testDelete() throws Exception {
        adminRestController.delete(UserTestData.USER_ID);
        Collection<User> users = adminRestController.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        adminRestController.delete(10);
    }
}
