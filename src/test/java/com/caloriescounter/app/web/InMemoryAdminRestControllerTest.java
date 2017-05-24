package com.caloriescounter.app.web;

import com.caloriescounter.app.UserTestData;
import com.caloriescounter.app.model.User;
import com.caloriescounter.app.repository.UserRepository;
import com.caloriescounter.app.util.exception.NotFoundException;
import com.caloriescounter.app.web.user.AdminRestController;
import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collection;

import static com.caloriescounter.app.UserTestData.USER;
import static com.caloriescounter.app.UserTestData.ADMIN;

/**
 * Created by Aleksandr_Shakhov on 24.05.17 22:10.
 */


public class InMemoryAdminRestControllerTest {

    private static ConfigurableApplicationContext context;
    private static AdminRestController adminRestController;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(context.getBeanDefinitionNames()) + "\n");
        adminRestController = context.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        context.close();
    }

    @Before
    public void setUp() throws Exception {
        UserRepository repository = context.getBean(UserRepository.class);
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
