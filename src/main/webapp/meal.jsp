<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
  <title>Meal</title>
</head>
<body>
<h3><a href="meals">Back</a></h3>
<hr>
<h2>Meal</h2>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals">
  <input type="hidden" name="mealId" value="${meal.id}"/>
  <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/>
  <hr>
  <input type="text" name="description" value="${meal.description}" required/>
  <hr>
  <input type="number" name="calories" value="${meal.calories}" required/>
  <hr>
  <input type="submit" value="Submit"/>
</form>
</body>
</html>


















<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <title>Meal</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h2><a href="index.html">Home</a> </h2>--%>
<%--<h3><a href="meals?action=meals">Back to Meal List</a></h3>--%>
<%--<hr>--%>
<%--<h2>Meal</h2>--%>

<%--<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>--%>
<%--<form method="post" action='meals'>--%>
<%--  <input type="hidden" name="id" value="${meal.id}"/>--%>
<%--  <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/>--%>
<%--  <hr>--%>
<%--  <input type="text" name="description" value="${meal.description}" required/>--%>
<%--  <hr>--%>
<%--  <input type="number" name="calories" value="${meal.calories}" required/>--%>
<%--  <hr>--%>
<%--  <input type="submit" value="Add">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>