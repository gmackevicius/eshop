<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Create new user!</h2>
            <form:form method="POST" modelAttribute="userModel">
                <div class="form-group">
                    <label for="name">Your name:</label>
                    <form:input path="name" cssClass="form-control" />
                    <form:errors path="name" cssStyle="color: red" />
                </div>
                <div class="form-group">
                    <label for="username">Your username:</label>
                    <form:input path="username" cssClass="form-control" />
                    <form:errors path="username" cssStyle="color: red" />
                </div>
                <div class="form-group">
                    <label for="password">Your password:</label>
                    <form:password path="password" cssClass="form-control" />
                    <form:errors path="password" cssStyle="color: red" />
                </div>
                <div class="form-group">
                    <label for="password">Confirm password:</label>
                    <form:password path="matchPassword" cssClass="form-control" />
                    <%--<c:if test="${not empty error}">--%>
                        <%--<span style="color: red">${error}</span>--%>
                    <%--</c:if>--%>
                    <form:errors path="matchPassword" cssStyle="color: red" />
                </div>
                <form:button class="btn btn-primary">Register</form:button>
            </form:form>

        </div>

    </div>
</div>
</body>
</html>
