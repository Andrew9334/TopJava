<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<jsp:useBean id="meals" scope="request" type="java.util.List"/>
<table border="1">
    <c:set var="lineColor" scope="session"/>
    <tr>
        <th>Date Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2"></th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <c:set var="lineColor" value="${meal.excess ? 'red' : 'green'}"/>
        <tr style="color:${lineColor}">
            <td><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy HH:mm"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="meals?action=save">Add Meal</a></p>

</body>
</html>