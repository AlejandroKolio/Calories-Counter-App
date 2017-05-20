package com.caloriescounter.app.util.exception;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 21:37.
 */


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
