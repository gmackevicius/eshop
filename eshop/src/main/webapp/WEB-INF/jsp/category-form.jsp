<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Create category</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<h1>Category form</h1>
<div class="container">
    <div class="row">
        <div class="col-4">
        <form:form method="POST"  modelAttribute="categoryModel">
            <div class="form-group">
                <label for="name">Category name</label>
                <form:input path="name" cssClass="form-control" />
                <form:errors path="name" cssStyle="color: red" />
            </div>

            <form:button class="btn btn-primary">Create</form:button>
        </form:form>
        </div>
        <div class="col-8">
            <ul class="list-group">
                <form:form method="DELETE" modelAttribute="categoryModel">
                <c:forEach items="${categoryList}" var="category">

                    <li class="list-group-item" style="display: flex; justify-content: space-between;">
                                ${category.getName()} <form:button name="id" value="${category.getId()}" class="btn btn-primary" >Delete</form:button>
                    </li>

                </c:forEach>
                </form:form>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
