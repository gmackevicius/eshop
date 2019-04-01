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
                    <div style="display: flex;">
                    <form:input path="name" cssClass="form-control" />
                    <form:button class="btn btn-primary">Create</form:button>
                    </div>
                    <form:errors path="name" cssStyle="color: red" />
                    </form:form>
                </div>

                <div class="form-group">
                    <form:form method="POST" action="/admin/create/authority" modelAttribute="authorityModel">
                    <label for="name">Authority name:</label>
                    <div style="display: flex;">
                    <form:input path="name" cssClass="form-control" />
                    <form:button class="btn btn-primary">Create</form:button>

                    </div>
                    <form:errors path="name" cssStyle="color: red" />
                    </form:form>
                </div>
            <c:choose>
                <c:when test="${trigger != true}">
                    <a href="/admin/delete-authorities/" class="btn btn-primary">Delete authorities</a>
                </c:when>
                <c:otherwise>
                    <a href="/admin/create/role" class="btn btn-primary">Delete authorities</a>
                </c:otherwise>
            </c:choose>

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
                        ${role.getName()}<div style="margin-left: 25%; display: flex;"><a href="/admin/give-authorities/${role.getId()}" style="margin-right: 5px;" class="btn btn-primary">Change authorities</a> <form:button name="id" value="${role.getId()}" class="btn btn-primary" >Delete</form:button></div>
                        </li >

                    </c:forEach>
                </form:form>
            </ul>
                    <c:if test="${triggered == true}">
                    <div>
                        <form:form  method="POST" action="/admin/give-authorities/${selectedRoleId}" modelAttribute="roleModel">
                        <ul class="list-group">
                            <li class="list-group-item-secondary" style="display: flex; flex-direction: column;">
                                <form:checkboxes path="id" items="${checked}" cssClass="form-check-input" itemLabel="name" itemValue="id" checked="checked" />
                            </li>
                            <li class="list-group-item-secondary" style="display: flex; flex-direction: column;">
                                <form:checkboxes path="id" items="${unchecked}" cssClass="form-check-input" itemLabel="name" itemValue="id"/>
                            </li>
                            <li class="list-group-item-secondary" style="display: flex;">
                                <form:button  class="btn btn-primary">Set</form:button>
                            </li>
                        </ul>
                        </form:form>
                    </div>
                    </c:if>
        </div>
    </div>
</div>

</body>
</html>
