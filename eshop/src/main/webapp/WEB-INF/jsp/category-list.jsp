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
</head>
<body>
<div class="container">
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
                            <a href="/${category.slug}/id-DESC">
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
                            <a href="/${slug}/price-ASC">
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
                            <a href="/${slug}/price-DESC">
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
                            <a href="/${slug}/name-ASC">
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
                <%--<li class="list-group-item">--%>
                <%--<form:form method="POST">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="name"><h5>Search for product</h5></label>--%>
                        <%--<form:input path="name" value="" cssClass="form-control" />--%>
                        <%--&lt;%&ndash;<form:errors path="name" cssStyle="color: red" />&ndash;%&gt;--%>
                        <%--<form:button class="btn btn-primary">Search</form:button>--%>
                    <%--</div>--%>
                <%--</form:form>    --%>
                <%--</li>--%>
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

                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">${product.getId()}</th>
                        <td>${product.getName()}</td>
                        <td>${product.getDescription()}</td>
                        <td>${product.getPrice()}</td>

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
