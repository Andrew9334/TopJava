<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<a href="users?action=create">Add User</a>
<br><br>
<table border="1" cellpadding="8" cellspacing="8">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    </thead>
    <c:forEach items="${requestScope.users}"
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt>Name</dt>
            <dd><input type="text" value="${user.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Email</dt>
            <dd><input type="text" value="${user.email}" name="email" required></dd>
        </dl>
    </form>

</table>
</body>
</html>