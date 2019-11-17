<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmx:setBundle basename="locale.client.previous_orders" var="previousOrdersB"/>
<%--@elvariable id="idDishMap" type="java.util.Map<java.lang.Integer, com.minsk.BSU.abliznets.cafe.entitie.Dish>"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.06.2019
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${previousOrdersB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${previousOrdersB}" key="head"/></h1>

            <%--@elvariable id="orders" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.order.Order>"--%>
            <c:forEach var="order" items="${orders}">
                <div class="entity">
                    <h3>
                        <fmx:message bundle="${previousOrdersB}" key="order.date.and.time"/> -
                        <c:forTokens items="${order.receiptTime.toString()}" var="token" delims="T">
                            <c:out value="${token} "/>
                        </c:forTokens>
                    </h3>

                    <p><fmx:message bundle="${previousOrdersB}" key="order.chosen.dishes"/>:</p>
                    <ul>
                        <c:forEach var="orderDish" items="${order.chosenDishes}">
                            <li>${idDishMap.get(orderDish.key).name} (x${orderDish.value})</li>
                        </c:forEach>
                    </ul>

                    <p><fmx:message bundle="${previousOrdersB}" key="order.result"/> - ${order.resultCost}$</p>

                    <p>
                        <fmx:message bundle="${previousOrdersB}" key="order.payment.method"/> -
                        <c:choose>
                            <c:when test="${order.paymentMethod == 'CREDIT_CARD'}">
                                <fmx:message bundle="${previousOrdersB}" key="payment.method.client.account"/>
                            </c:when>
                            <c:when test="${order.paymentMethod == 'CASH'}">
                                <fmx:message bundle="${previousOrdersB}" key="payment.method.cash"/>
                            </c:when>
                        </c:choose>
                    </p>

                    <form id="rating-form" action="<c:url value="/command"/>" method="post">
                        <input type="hidden" name="command" value="rate_order">
                        <input type="hidden" name="navigationWay" value="redirect">
                        <input type="hidden" name="orderID" value="${order.ID}">

                        <label><fmx:message bundle="${previousOrdersB}" key="order.rating"/></label>
                        <div class="rating_block">
                            <c:choose>
                                <c:when test="${order.score == 5}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="5"
                                           id="rating_5" type="radio" checked/>
                                    <label for="rating_5" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="5"
                                           id="rating_5" type="radio"/>
                                    <label for="rating_5" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 4}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="4"
                                           id="rating_4" type="radio" checked/>
                                    <label for="rating_4" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="4"
                                           id="rating_4" type="radio"/>
                                    <label for="rating_4" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 3}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="3"
                                           id="rating_3" type="radio" checked/>
                                    <label for="rating_3" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="3"
                                           id="rating_3" type="radio"/>
                                    <label for="rating_3" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 2}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="2"
                                           id="rating_2" type="radio" checked/>
                                    <label for="rating_2" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="2"
                                           id="rating_2" type="radio"/>
                                    <label for="rating_2" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 1}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="1"
                                           id="rating_1" type="radio" checked/>
                                    <label for="rating_1" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="1"
                                           id="rating_1" type="radio"/>
                                    <label for="rating_1" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>

                    <form action="<c:url value="/command"/>" method="post">
                        <label for="order-comment">
                            <fmx:message bundle="${previousOrdersB}" key="order.comment"/>
                        </label>
                        <c:choose>
                            <c:when test="${order.comment != null}">
                                <c:set value="${order.comment}" var="orderComment" scope="page"/>
                            </c:when>
                            <c:otherwise>
                                <c:set value="" var="orderComment" scope="page"/>
                            </c:otherwise>
                        </c:choose>
                        <textarea id="order-comment" name="comment" maxlength="1000"
                                  required>${orderComment}</textarea>

                        <input type="hidden" name="command" value="leave_comment">
                        <input type="hidden" name="navigationWay" value="redirect">
                        <input type="hidden" name="orderID" value="${order.ID}">
                        <button type="submit">
                            <fmx:message bundle="${previousOrdersB}"
                                         key="form.leave.comment.button.leave.comment"/>
                        </button>
                    </form>
                </div>
            </c:forEach>

            <div class="horizontal-centered">
                <%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
                <%--@elvariable id="pageCount" type="java.lang.Integer"--%>
                <%--@elvariable id="currentPageNumber" type="java.lang.Integer"--%>
                <ctg:paginator recordsCount="${recordsCount}" pageCount="${pageCount}"
                               currentPageNumber="${currentPageNumber}" command="get_previous_orders"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
<script src="js/main.js"></script>
</body>
</html>
