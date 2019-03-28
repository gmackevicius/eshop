
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Product form</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Product form</h1>
<div class="container">
    <div class="row">
        <form:form method="POST"  modelAttribute="productModel">
            <div class="form-group">
                <label for="name">Product name</label>
                <form:input path="name" cssClass="form-control" />
                <form:errors path="name" cssStyle="color: red" />
            </div>
            <div class="form-group">
                <label for="description">Product description</label>
                <form:textarea rows="2" cols="2" path="description" cssClass="form-control" />
                <form:errors path="description" cssStyle="color: red" />
            </div>
            <div class="form-group">
                <label for="price">Product price</label>
                <form:input path="price" cssClass="form-control" />
                <form:errors path="price" cssStyle="color: red" />
            </div>
            <div class="form-group">
                <label for="categoryId">Product category</label>
                <form:select path="categoryId">
                    <form:option value="0" label="--Please Select"/>
                    <form:options items="${categoryList}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="categoryId" cssStyle="color: red" />
            </div>
            <form:button class="btn btn-primary">Create</form:button>
        </form:form>
    </div>
</div>
</body>
</html>
