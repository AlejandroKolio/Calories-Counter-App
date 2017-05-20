package com.caloriescounter.app.web.meal;

import com.caloriescounter.app.AuthorizedUser;
import com.caloriescounter.app.model.Meal;
import com.caloriescounter.app.service.MealService;
import com.caloriescounter.app.to.MealWithExceeded;
import com.caloriescounter.app.util.DateTimeUtil;
import com.caloriescounter.app.util.MealsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.caloriescounter.app.util.ValidationUtil.checkIdConsistent;
import static com.caloriescounter.app.util.ValidationUtil.checkNew;

/**
 * Created by Aleksandr_Shakhov on 11.05.17 20:50.
 */

@Controller
public class MealRestController {

    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<MealWithExceeded> getAll() {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        return MealsUtil.getNonFiltratedMealsWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", meal, userId);
        checkNew(meal);
        return service.save(meal, userId);
    }

    public void update(Meal meal, int id) {
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", meal, userId);
        checkIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public List<MealWithExceeded> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        LOG.info("getBetween dates({} - {}) time({} - {}) for User {}", startDate, endDate, startTime, endTime, userId);

        return MealsUtil.getFiltratedMealsWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()
        );
    }
}
