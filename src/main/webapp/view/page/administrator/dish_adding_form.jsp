<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.administrator.dish_adding_form" var="dishAddingFormB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.06.2019
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${dishAddingFormB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${dishAddingFormB}" key="head"/></h1>

            <div class="form-area">
                <c:import url="/view/page/administrator/dish_form_fields.jsp"/>

                <button onclick="history.back()">
                    <fmx:message bundle="${dishAddingFormB}" key="button.cancel"/>
                </button>

                <form action="<c:url value="/command"/>" method="post" id="dish-form">
                    <input type="hidden" name="command" value="add_dish">
                    <input type="hidden" name="navigationWay" value="redirect">
                    <button type="submit">
                        <fmx:message bundle="${dishAddingFormB}" key="form.add.dish.button.add"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
