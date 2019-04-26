
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Order</title>
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


                </tr>
                </thead>
                <c:forEach items="${orderModel.getItems()}" var="cartItem">
                    <tbody>
                    <tr>
                        <td>${cartItem.getProduct().getName()}</td>
                        <td>${cartItem.getProduct().getPrice()}</td>
                        <td>${cartItem.getQuantity()}</td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>


    </div>
    <div class="row">
        <div class="col-1">
            <span>Total: ${orderModel.getSum()}</span>
        </div>
        <div class="col-1">
            <form:form method="POST" action="/shopping-cart/order" modelAttribute="orderModel">
                <form:button name="id" value="${orderModel.getId()}" class="btn btn-danger">Pay</form:button>
            </form:form>
        </div>
    </div>

</div>

</body>
</html>
