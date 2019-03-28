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
        </div>
        <div class="col-8">
            <ul class="list-group">
                <form:form method="POST" action="/admin/give-authorities/{id}" modelAttribute="roleModel">
                    <c:forEach items="${roleList}" var="role">

                        <li class="list-group-item" style="display: flex; justify-content: space-between;">
                        ${role.getName()} <a href="/admin/give-authorities/${role.getId()}" class="btn btn-primary">Give authorities</a>

                        </li>
                        <c:if test="${authorityList != null}">
                            <ul class="list-group">
                                <c:forEach items="${authorityList}" var="auth">
                                    <li class="list-group-item-secondary" style="display: flex;">
                                        <form:checkbox path="id" cssClass="form-check-input" cssStyle="margin-left: 80%;" value="${auth.getId()}"/>
                                        <span style="margin-left: 5%;">${auth.getName()}</span>
                                    </li>
                                </c:forEach>
                                <form:button name=""  class="btn btn-primary">Set</form:button>
                            </ul>
                        </c:if>

                    </c:forEach>
                </form:form>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
