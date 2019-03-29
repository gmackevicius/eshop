<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="action" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role/Authority creation</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-4">
                <div class="form-group">
                    <form:form method="POST" action="/admin/create/role" modelAttribute="roleModel">
                    <label for="name">Role name:</label>
                    <form:input path="name" cssClass="form-control" />
                    <form:errors path="name" cssStyle="color: red" />
                    <form:button class="btn btn-primary">Create</form:button>
                    </form:form>
                </div>

                <div class="form-group">
                    <form:form method="POST" action="/admin/create/authority" modelAttribute="authorityModel">
                    <label for="name">Authority name:</label>
                    <form:input path="name" cssClass="form-control" />
                    <form:errors path="name" cssStyle="color: red" />
                    <form:button class="btn btn-primary">Create</form:button>
                    </form:form>
                </div>
                <a href="/admin/give-authorities/" class="btn btn-primary">Change authorities</a>
                <a href="/admin/delete-authorities/" class="btn btn-primary">Delete authorities</a>
                <c:if test="${trigger == true}">
                    <ul class="list-group">
                        <form:form method="POST" action="/admin/delete-authorities/" modelAttribute="authorityModel">
                            <c:forEach items="${authorityList}" var="auth">
                            <li class="list-group-item" style="display: flex; justify-content: space-between;">
                                <span>${auth.getName()}</span>
                                <form:checkbox path="id" cssClass="form-check-input"  value="${auth.getId()}"/>
                            </li>
                            </c:forEach>
                            <li class="list-group-item" style="display: flex; justify-content: space-between;"><form:button class="btn btn-primary">DELETE</form:button></li>

                        </form:form>
                    </ul>
                </c:if>
        </div>
        <div class="col-8">
            <ul class="list-group">
                <form:form method="POST" action="/admin/delete-role/" modelAttribute="roleModel">
                    <c:forEach items="${roleList}" var="role">

                        <li class="list-group-item" style="display: flex; justify-content: space-between;">
                        ${role.getName()} <form:button name="id" value="${role.getId()}" class="btn btn-primary" >Delete</form:button>
                        </li>
                        <c:if test="${authorityList != null and selected == true}">
                            <ul class="list-group">
                                <c:forEach items="${authorityList}" var="auth">
                                    <c:forEach items="${role.getAuthorities()}" var="a">
                                    <li class="list-group-item-secondary" style="display: flex;">
                                        <c:choose>
                                        <c:when test="${a.getId() == auth.getId()}">
                                        <form:checkbox path="id" cssClass="form-check-input" cssStyle="margin-left: 80%;" value="${auth.getId()}" checked="checked" />
                                        <span style="margin-left: 5%;">${auth.getName()}</span>
                                        </c:when>
                                        <c:otherwise>
                                        <form:checkbox path="id" cssClass="form-check-input" cssStyle="margin-left: 80%;" value="${auth.getId()}"/>
                                        <span style="margin-left: 5%;">${auth.getName()}</span>
                                        </c:otherwise>
                                        </c:choose>
                                    </li>
                                    </c:forEach>
                                </c:forEach>

                            </ul>
                        </c:if>

                    </c:forEach>
                    <c:if test="${authorityList != null and selected == true}">
                    <form:button class="btn btn-primary">Set</form:button>
                    </c:if>
                </form:form>
            </ul>

        </div>
    </div>
</div>

</body>
</html>
