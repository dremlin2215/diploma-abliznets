<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmx:setBundle basename="locale.administrator.all_dishes" var="allDishesB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2019
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${allDishesB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${allDishesB}" key="head"/></h1>

            <div class="content-actions">
                <form action="<c:url value="/command"/>">
                    <input type="hidden" name="command" value="specify_new_dish">
                    <input type="hidden" name="navigationWay" value="forward">
                    <button type="submit">
                        <fmx:message bundle="${allDishesB}" key="form.specify.new.dish.button.add.new.dish"/>
                    </button>
                </form>
            </div>

            <%--@elvariable id="allDishes" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.Dish>"--%>
            <c:forEach items="${allDishes}" var="dish">
                <div class="entity">
                    <h3><fmx:message bundle="${allDishesB}" key="dish.name"/> - ${dish.name}</h3>
                    <p><fmx:message bundle="${allDishesB}" key="dish.cost"/> - ${dish.cost}$</p>
                    <p>
                        <fmx:message bundle="${allDishesB}" key="dish.description"/>:<br>${dish.description}
                    </p>
                    <p><fmx:message bundle="${allDishesB}" key="dish.is.in.client.menu"/> -
                        <c:choose>
                            <c:when test="${dish.inMenu}">
                                <fmx:message bundle="${allDishesB}" key="in.client.menu.yes"/>
                            </c:when>
                            <c:otherwise>
                                <fmx:message bundle="${allDishesB}" key="in.client.menu.no"/>
                            </c:otherwise>
                        </c:choose>
                    </p>


                    <%--<img src="${pageContext.request.contextPath}/view/image/${dish.imageHref}" alt="${dish.name}">--%>

                    <form action="<c:url value="/command"/>">
                        <input type="hidden" name="command" value="manage_dish_information">
                        <input type="hidden" name="navigationWay" value="forward">
                        <input type="hidden" name="dishID" value=${dish.ID}>
                        <button type="submit">
                            <fmx:message bundle="${allDishesB}"
                                         key="form.manage.dish.information.button.manage"/>
                        </button>
                    </form>
                    <form action="<c:url value="/command"/>" method="post">
                        <input type="hidden" name="command" value="delete_dish">
                        <input type="hidden" name="navigationWay" value="redirect">
                        <input type="hidden" name="dishID" value=${dish.ID}>
                        <button id="delete-button" type="submit">x</button>
                    </form>
                </div>
            </c:forEach>

            <div class="horizontal-centered">
                <%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
                <%--@elvariable id="pageCount" type="java.lang.Integer"--%>
                <%--@elvariable id="currentPageNumber" type="java.lang.Integer"--%>
                <ctg:paginator recordsCount="${recordsCount}" pageCount="${pageCount}"
                               currentPageNumber="${currentPageNumber}" command="get_all_dishes"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
