package com.caloriescounter.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aleksandr_Shakhov on 29.04.17 19:05.
 */


public class UserServlet extends javax.servlet.http.HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to User List");
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }
}
