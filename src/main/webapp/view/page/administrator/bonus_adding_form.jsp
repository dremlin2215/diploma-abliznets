<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.administrator.bonus_adding_form" var="bonusAddingFormB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 20.06.2019
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${bonusAddingFormB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${bonusAddingFormB}" key="head"/></h1>

            <div class="form-area">
                <div class="form-area-field">
                    <label for="bonus-name">
                        <fmx:message bundle="${bonusAddingFormB}" key="bonus.name"/>
                    </label>
                    <input type="text" id="bonus-name" name="bonusName"
                           form="bonus-form" required><br>
                </div>
                <div class="form-area-field">
                    <label for="bonus-description">
                        <fmx:message bundle="${bonusAddingFormB}" key="bonus.description"/>
                    </label>
                    <textarea id="bonus-description" name="bonusDescription"
                              form="bonus-form" required></textarea><br>
                </div>

                <button onclick="history.back()">
                    <fmx:message bundle="${bonusAddingFormB}" key="button.cancel"/>
                </button>

                <form action="<c:url value="/command"/>" method="post" id="bonus-form">
                    <input type="hidden" name="command" value="add_bonus">
                    <input type="hidden" name="navigationWay" value="redirect">
                    <button type="submit">
                        <fmx:message bundle="${bonusAddingFormB}" key="form.add.bonus.button.add"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
