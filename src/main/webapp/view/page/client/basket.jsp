<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.client.basket" var="basketB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 12.06.2019
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${basketB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body onload="setLimits()">
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${basketB}" key="head"/></h1>
            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th><fmx:message bundle="${basketB}" key="table.head.name"/></th>
                        <th><fmx:message bundle="${basketB}" key="table.head.cost"/></th>
                        <th><fmx:message bundle="${basketB}" key="table.head.servings.number"/></th>
                        <th><fmx:message bundle="${basketB}" key="table.head.total.cost"/></th>
                        <th><fmx:message bundle="${basketB}" key="table.head.remove.from.order"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="dishes" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.Dish>"--%>
                    <c:forEach items="${dishes}" var="dish">
                        <tr>
                            <td>${dish.name}</td>

                            <c:set var="dishCost" value="${dish.cost}" scope="page"/>
                            <td>${dishCost}$</td>

                            <c:set var="dishID" value="${dish.ID}" scope="page"/>
                                <%--@elvariable id="order" type="java.util.Map<Integer, Integer>"--%>
                            <c:set var="servingsNumber" value="${order.get(dishID)}" scope="page"/>
                            <td>${servingsNumber}</td>

                                <%--@elvariable id="generalCosts" type="java.util.Map<Integer, java.math.BigDecimal>"--%>
                            <td>${generalCosts.get(dishID)}$</td>

                            <td>
                                <form action="<c:url value="/command"/>" method="post">
                                    <input type="hidden" name="command" value="remove_dish_order">
                                    <input type="hidden" name="navigationWay" value="redirect">
                                    <input type="hidden" name="selectedDishID" value="${dishID}">
                                    <button type="submit">
                                        <fmx:message bundle="${basketB}" key="form.remove.button.remove"/>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="4">
                            <%--@elvariable id="resultCost" type="java.math.BigDecimal"--%>
                            <fmx:message bundle="${basketB}" key="table.foot.result"/> - ${resultCost}$
                        </td>
                        <td>
                            <form action="<c:url value="/command"/>" method="post">
                                <input type="hidden" name="command" value="remove_all_dish_order">
                                <input type="hidden" name="navigationWay" value="redirect">
                                <button type="submit">
                                    <fmx:message bundle="${basketB}" key="form.remove.all.button.all"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                    </tfoot>
                </table>

                <form action="<c:url value="/command"/>" method="post">
                    <label for="date">
                        <fmx:message bundle="${basketB}" key="form.order.label.choose.date"/>
                    </label><br>
                    <input id="date" type="date" name="date" onchange="setNewTimeLimits()" required><br>
                    <label for="time">
                        <fmx:message bundle="${basketB}" key="form.order.label.choose.time"/>
                    </label><br>
                    <input id="time" type="time" name="time" required><br>
                    <label for="payment-method">
                        <fmx:message bundle="${basketB}" key="form.order.label.choose.payment.method"/>
                    </label><br>
                    <select id="payment-method" name="paymentMethod" required>
                        <option value="cash">
                            <fmx:message bundle="${basketB}"
                                         key="form.order.select.choose.payment.method.option.cash"/>
                        </option>
                        <option value="credit_card">
                            <fmx:message bundle="${basketB}"
                                         key="form.order.select.choose.payment.method.option.client.account"/>
                        </option>
                    </select><br>
                    <input type="hidden" name="command" value="order">
                    <input type="hidden" name="navigationWay" value="redirect">
                    <button type="submit">
                        <fmx:message bundle="${basketB}" key="form.order.button.order"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
<script src="js/main.js"></script>
</body>
</html>
