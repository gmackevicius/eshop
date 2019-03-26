<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product list</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <form:form method="POST"  modelAttribute="productModel">
        <div class="col-lg-6">
                <c:forEach items="${productList}" var="product">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Description</th>
                            <th scope="col">Price</th>
                            <th scope="col">Category</th>
                            <th scope="col">Select</th>
                            <th scope="col">Edit</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">${product.getId()}</th>
                            <td>${product.getName()}</td>
                            <td>${product.getDescription()}</td>
                            <td>${product.getPrice()}</td>
                            <td>${product.getCategoryName()}</td>
                            <td><div class="form-check">
                                <form:checkbox path="id" cssClass="form-check-input" value="${product.getId()}"/>
                            </div></td>
                            <td><a href="create/products/${product.getId()}" class="btn btn-dark">Edit</a></td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
        </div>
            <div class="col-lg-6">
                <form:button class="btn btn-primary">Delete</form:button>
            </div>
        </form:form>

    </div>

</div>


</body>
</html>
