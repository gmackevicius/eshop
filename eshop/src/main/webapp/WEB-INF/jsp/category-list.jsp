<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019-03-19
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category list</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <br>
    <div class="row">
        <div class="col-11">
        </div>
        <div class="col-1">
            <span style="color: darkred; font-weight: bold; font-size: 30px;">${shoppingCart.getTotalQuantity()}</span>
            <a href="/shopping-cart"><i class="fas fa-shopping-cart" style="font-size: 32px;"></i></a>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-2">
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="/category-list/id-DESC">
                        All categories
                    </a>
                </li>
                <c:forEach items="${categoryList}" var="category">
                <li class="list-group-item">
                    <c:choose>
                        <c:when test="${!category.slug.equals(slug)}">
                            <a href="/category-list/${category.slug}/id-DESC">
                                    ${category.name}
                            </a>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-primary"> ${category.name}</span>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
            </ul>

            <ul class="list-group list-group-flush">
                <br>
                <h5>Sort by:</h5>
                <li class="list-group-item">
                    <c:choose>
                        <c:when test="${slug.length() > 0}">
                            <a href="/category-list/${slug}/price-ASC">
                                price from lowest
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/category-list/price-ASC">
                                price from lowest
                            </a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="list-group-item">
                    <c:choose>
                        <c:when test="${slug.length() > 0}">
                            <a href="/category-list/${slug}/price-DESC">
                                price from highest
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/category-list/price-DESC">
                                price from highest
                            </a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="list-group-item">
                    <c:choose>
                        <c:when test="${slug.length() > 0}">
                            <a href="/category-list/${slug}/name-ASC">
                               name
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/category-list/name-ASC">
                                name
                            </a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="list-group-item">

                    <div class="form-group">
                        <form:form method="GET" modelAttribute="filterModel">
                        <label for="name"><h5>Search for product</h5></label>
                        <form:input path="name" cssClass="form-control" />
                        <%--<form:errors path="name" cssStyle="color: red" />--%>
                        <form:button class="btn btn-primary">Search</form:button>
                        </form:form>
                    </div>

                </li>
            </ul>

        </div>
        <div class="col-10">
            <c:forEach items="${productList}" var="product">
                <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th style="width:10%" scope="col">#</th>
                        <th style="width:25%" scope="col">Title</th>
                        <th style="width:55%" scope="col">Description</th>
                        <th style="width:10%" scope="col">Price</th>
                        <th style="width:10%" scope="col"></th>

                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">${product.getId()}</th>
                        <td>${product.getName()}</td>
                        <td>${product.getDescription()}</td>
                        <td>${product.getPrice()}</td>
                        <form:form method="POST" modelAttribute="productModel" action="/category-list/buy">
                        <td><form:button name="id" value="${product.getId()}" class="btn btn-primary">Buy</form:button></td>
                        </form:form>
                    </tr>
                    </tbody>
                </table>
                </div>
            </c:forEach>

        </div>
    </div>
</div>

</body>
</html>
