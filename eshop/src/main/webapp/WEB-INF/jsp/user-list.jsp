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

            <div class="col-6">
                <c:forEach items="${userList}" var="user">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col" style="width: 20%;">Name</th>
                            <th scope="col" style="width: 20%;">Username</th>
                            <th scope="col" style="width: 40%;">Roles</th>
                            <th scope="col" style="width: 10%;"></th>
                            <th scope="col" style="width: 10%;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">${user.getName()}</th>
                            <td>${user.getUsername()}</td>
                            <c:forEach items="${user.getRoles()}" var="r">
                            <td>${r.getName()}</td>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${triggered != true}">
                                    <td><a href="/admin/user-list/edit-roles/${user.getId()}" class="btn btn-dark">Edit Roles</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="/admin/user-list" class="btn btn-dark">Edit Roles</a></td>
                                </c:otherwise>
                            </c:choose>

                        </tr>
                        </tbody>
                    </table>
                </c:forEach>


            </div>
            <div class="col-4">
                    <c:if test="${triggered == true and roleList != null}">
                        <form:form method="POST"  modelAttribute="userModel">
                            <ul class="list-group">
                                <li class="list-group-item" style="display: flex; flex-direction: column;">

                                        <c:forEach items="${roleList}" var="role">
                                            <c:forEach items="${user.getRoles()}" var="ur">
                                            <c:choose>
                                                <c:when test="${user.getRoles().size() > 0 and ur.getId() == role.getId()}">
                                                    <form:checkbox path="id" label="${role.getName()}" value="${role.getId()}" checked="checked"/>
                                                </c:when>
                                                <%--<c:when test="${user.getRoles().size() == 0 or user.getRoles() == null}">--%>
                                                    <%--<form:checkbox path="id" label="${role.getName()}" value="${role.getId()}"/>--%>
                                                <%--</c:when>--%>
                                                <c:otherwise>
                                                    <form:checkbox path="id" label="${role.getName()}" value="${role.getId()}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            </c:forEach>
                                        </c:forEach>

                                    <%--<form:checkboxes path="id" test="" items="${roleList}" itemLabel="name" itemValue="id" />--%>
                                </li>
                                <li class="list-group-item"><form:button class="btn btn-primary">Set</form:button></li>
                            </ul>
                        </form:form>
                    </c:if>


            </div>

    </div>

</div>
</body>
</html>
