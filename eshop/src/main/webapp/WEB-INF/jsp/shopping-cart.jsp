
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Shopping cart</title>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">

            <div class="col-10">

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col"></th>

                        </tr>
                        </thead>
                        <c:forEach items="${shoppingCart.getCartItems()}" var="cartItem">
                        <tbody>
                        <tr>
                            <td>${cartItem.getProduct().getName()}</td>
                            <td>${cartItem.getProduct().getPrice()}</td>
                            <td>
                                <form:form method="POST" modelAttribute="cartItem">
                                <form:input path="quantity" value="${cartItem.getQuantity()}"/>
                                </form:form>
                            </td>
                            <td><div class="form-check">
                                <form:form method="POST" action="/shopping-cart/remove" modelAttribute="cartItem">
                                <form:button name="id" class="btn btn-danger" value="${cartItem.getId()}">Remove</form:button>
                                </form:form>
                            </div></td>

                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
            </div>


    </div>
    <div class="row">
        <div class="col-1">
            <span>Total: ${shoppingCart.getSum()}</span>
        </div>
    </div>

</div>

</body>
</html>
