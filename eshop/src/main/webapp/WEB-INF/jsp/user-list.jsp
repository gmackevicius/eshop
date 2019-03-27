<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <form:form method="POST"  modelAttribute="userModel">
            <div class="col-6">
                <c:forEach items="${userList}" var="user">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Username</th>
                            <th scope="col">Roles</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">${user.getName()}</th>
                            <td>${user.getUsername()}</td>
                            <c:forEach items="${user.getRoles()}" var="r">
                            <td>${r}</td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </form:form>

    </div>

</div>
</body>
</html>
