<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meal</h2>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals"><br/>
    <label> DateTime : <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/> </label> <br/>
    <label> Description : <input type="text" name="description" value="${meal.description}" required/></label> <br/>
    <label> Calories <input type="number" name="calories" value="${meal.calories}" required/> </label> <br/>
    <button type="submit">Add</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>