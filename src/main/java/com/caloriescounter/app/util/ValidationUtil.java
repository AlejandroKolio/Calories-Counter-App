package com.caloriescounter.app.util;

import com.caloriescounter.app.model.BaseEntity;
import com.caloriescounter.app.util.exception.NotFoundException;

/**
 * Created by Aleksandr_Shakhov on 13.05.17 13:31.
 */


public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id = " + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id = " + id);
    }

    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(object != null, message);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + message);
        }
    }

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id = null)");
        }
    }

    public static void checkIdConsistent(BaseEntity entity, int id) {
        //http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id = " + id);
        }
    }
}
