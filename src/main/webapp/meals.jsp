
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
            <td><a href="meals?action=edit&id=${meal.id}"></a>update</td>
            <td><a href="meals?action=delete&id=${meal.id}"></a>delete</td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="meals" name="AddMeal">
    DateTime : <input type="datetime-local" name="DataTime"/> <br />
    Description : <input type="text" name="Description"/> <br/>
    Calories " <input type="number" name="Calories"/> <br/>
    <button type="submit">Add</button>
<%--<p><a href="meals?action=insert">Add Meal</a></p>--%>
</body>
</html>














<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Meal list</title>--%>
<%--    <style>--%>
<%--        .green {--%>
<%--            color: green;--%>
<%--        }--%>
<%--        .red {--%>
<%--            color: red;--%>
<%--        }--%>
<%--    </style>--%>

<%--</head>--%>
<%--<body>--%>
<%--<h3><a href="index.html">Home</a> </h3>--%>
<%--<hr>--%>
<%--<h2>Meals</h2>--%>
<%--<jsp:useBean id="meals" scope="request" type="java.util.List"/>--%>
<%--<table border="1">--%>
<%--    <c:set var="lineColor" scope="session"/>--%>
<%--    <tr>--%>
<%--        <th>Date Time</th>--%>
<%--        <th>Description</th>--%>
<%--        <th>Calories</th>--%>
<%--        <th colspan="2"></th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="meal" items="${meals}">--%>
<%--        <c:set var="lineColor" value="${meal.excess ? red : green}"/>--%>
<%--        <tr>--%>
<%--            <td>${meal.dateTime}</td>--%>
<%--            <td>${meal.description}</td>--%>
<%--            <td>${meal.calories}</td>--%>
<%--            <td><a href="meals?action=update&id=${meal.id}"></a>update</td>--%>
<%--            <td><a href="meals?action=delete&id=${meal.id}"></a>delete</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<%--&lt;%&ndash;<p><a href="meals?action=add">Add Meal</a> </p>&ndash;%&gt;--%>

<%--<h3>Add</h3>--%>

<%--<form method="post" action="meals" name="AddMeal">--%>
<%--    DateTime : <input type="datetime-local" name="DataTime"/> <br />--%>
<%--    Description : <input type="text" name="Description"/> <br/>--%>
<%--    Calories " <input type="number" name="Calories"/> <br/>--%>
<%--    <button type="submit">Add</button>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
