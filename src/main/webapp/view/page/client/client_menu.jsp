<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="custom-tags"%>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.client.client_menu" var="clientMenuB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 27.04.2019
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${clientMenuB}" key="title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${clientMenuB}" key="head"/></h1>

            <%--@elvariable id="dishesInMenu" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.Dish>"--%>
            <c:forEach items="${dishesInMenu}" var="dish">
                <div class="entity">
                    <h3><fmx:message bundle="${clientMenuB}" key="dish.name"/> - ${dish.name}</h3>
                    <p><fmx:message bundle="${clientMenuB}" key="dish.cost"/> - ${dish.cost}$</p>
                    <p><fmx:message bundle="${clientMenuB}" key="dish.description"/>:<br>${dish.description}</p>
                    <img src="${pageContext.request.contextPath}/view/image/${dish.imageHref}" alt="${dish.name}">

                    <form action="<c:url value="/command"/>" method="post">
                        <input type="hidden" name="command" value="save_dish_order">
                        <input type="hidden" name="navigationWay" value="redirect">
                        <input type="hidden" name="selectedDishID" value="${dish.ID}">
                        <label for="servings-number">
                            <fmx:message bundle="${clientMenuB}"
                                         key="form.save.to.basket.label.choose.servings.number"/>
                        </label><br>
                        <input id="servings-number" type="number" name="servingsNumber" min="1"
                               required><br>
                        <button type="submit">
                            <fmx:message bundle="${clientMenuB}" key="form.save.to.basket.button.save.to.basket"/>
                        </button>
                    </form>
                </div>
            </c:forEach>

            <div class="horizontal-centered">
                <%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
                <%--@elvariable id="pageCount" type="java.lang.Integer"--%>
                <%--@elvariable id="currentPageNumber" type="java.lang.Integer"--%>
                <ctg:paginator recordsCount="${recordsCount}" pageCount="${pageCount}"
                               currentPageNumber="${currentPageNumber}" command="get_client_menu"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
