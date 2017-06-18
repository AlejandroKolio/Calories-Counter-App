package com.caloriescounter.app;

import com.caloriescounter.app.to.MealWithExceeded;
import com.caloriescounter.app.model.Role;
import com.caloriescounter.app.model.User;
import com.caloriescounter.app.web.meal.MealRestController;
import com.caloriescounter.app.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aleksandr_Shakhov on 12.05.17 21:00.
 */


public class SpringMain {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/mock.xml"))
        {
            System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
            AdminRestController adminRestController = context.getBean(AdminRestController.class);
            adminRestController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            System.out.println("\n");

            MealRestController mealRestController = context.getBean(MealRestController.class);
            List<MealWithExceeded> meals = mealRestController.getBetween(
                    LocalDate.of(2017, Month.APRIL, 26), LocalTime.of(9,00),
                    LocalDate.of(2017, Month.APRIL, 27), LocalTime.of(9, 00));
            meals.forEach(System.out::println);
        }
    }
}
