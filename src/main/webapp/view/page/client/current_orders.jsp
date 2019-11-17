<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmx:setBundle basename="locale.client.current_orders" var="currentOrdersB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.06.2019
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${currentOrdersB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${currentOrdersB}" key="head"/></h1>

            <%--@elvariable id="orders" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.order.Order>"--%>
            <c:forEach var="order" items="${orders}">
                <div class="entity">
                    <h3>
                        <fmx:message bundle="${currentOrdersB}" key="order.date.and.time"/> -
                        <c:forTokens items="${order.receiptTime.toString()}" var="token" delims="T">
                            <c:out value="${token} "/>
                        </c:forTokens>
                    </h3>

                    <p><fmx:message bundle="${currentOrdersB}" key="order.chosen.dishes"/>:</p>
                    <ul>
                        <c:forEach var="orderDish" items="${order.chosenDishes}">
                            <li>${idDishMap.get(orderDish.key).name} (x${orderDish.value})</li>
                        </c:forEach>
                    </ul>

                    <p>
                        <fmx:message bundle="${currentOrdersB}" key="order.result"/>
                        - ${order.resultCost}$
                    </p>

                    <p>
                        <fmx:message bundle="${currentOrdersB}" key="order.payment.method"/> -
                        <c:choose>
                            <c:when test="${order.paymentMethod == 'CREDIT_CARD'}">
                                <fmx:message bundle="${currentOrdersB}" key="payment.method.client.account"/>
                            </c:when>
                            <c:when test="${order.paymentMethod == 'CASH'}">
                                <fmx:message bundle="${currentOrdersB}" key="payment.method.cash"/>
                            </c:when>
                        </c:choose>
                    </p>

                    <form action="command" method="post">
                        <input type="hidden" name="command" value="delete_order">
                        <input type="hidden" name="navigationWay" value="redirect">
                        <input type="hidden" name="orderID" value="${order.ID}">
                        <button type="submit">
                            <fmx:message bundle="${currentOrdersB}" key="form.remove.order.button.remove"/>
                        </button>
                    </form>
                </div>
            </c:forEach>

            <div class="horizontal-centered">
                <%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
                <%--@elvariable id="pageCount" type="java.lang.Integer"--%>
                <%--@elvariable id="currentPageNumber" type="java.lang.Integer"--%>
                <ctg:paginator recordsCount="${recordsCount}" pageCount="${pageCount}"
                               currentPageNumber="${currentPageNumber}" command="get_current_orders"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
