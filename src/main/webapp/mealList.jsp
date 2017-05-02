<%@ page import="com.caloriescounter.app.util.DateTimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: alexandershakhov
  Date: 01.05.17
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <h4><a href="index.html">Home</a></h4>
    <title>Meal List</title>
    <style>
        .normal { color: green }
        .exceeded { color: red }
    </style>
</head>
<body>

<section>
    <h3>Meal List</h3>
    <a href="meals?action=create">Add meal</a>
    <hr>
    <p>Here is the information about your meals!</p>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
            </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="com.caloriescounter.app.model.MealWithExceeded" />

            <tr class="${meal.exceeded ? 'exceeded' : 'normal'}">
                <td><%=DateTimeUtil.toString(meal.getDateTime())%></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>

            </tr>

        </c:forEach>
    </table>

</section>

</html>
