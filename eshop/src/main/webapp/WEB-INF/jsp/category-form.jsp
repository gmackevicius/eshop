<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019-03-14
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create category</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1>Category form</h1>
<div class="container">
    <div class="row">
        <form:form method="POST"  modelAttribute="categoryModel">
            <div class="form-group">
                <label for="name">Category name</label>
                <form:input path="name" cssClass="form-control" />
                <form:errors path="name" cssStyle="color: red" />
            </div>

            <form:button class="btn btn-primary">Create</form:button>
        </form:form>
    </div>
</div>
</body>
</html>
