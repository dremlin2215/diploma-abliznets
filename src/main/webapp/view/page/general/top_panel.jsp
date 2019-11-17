<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmx:setBundle basename="locale.general.top_panel" var="topPanelB"/>
<%--@elvariable id="user" type="com.minsk.BSU.abliznets.cafe.entitie.user.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.04.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${topPanelB}" key="title"/></title>
</head>
<body>
<ul class="horizontal-menu">
    <li>
        <form action="<c:url value="/command"/>" method="post">
            <input type="hidden" name="command" value="logout">
            <input type="hidden" name="navigationWay" value="forward">
            <button type="submit">
                <fmx:message bundle="${topPanelB}" key="form.sign.out.button.sign.out"/>
            </button>
        </form>
    </li>
    <li>
        <button type="submit">
            <fmx:message bundle="${topPanelB}" key="button.change.language"/>
        </button>
        <ul class="sub-menu">
            <li>
                <form action="<c:url value="/command"/>" method="post">
                    <input type="hidden" name="command" value="choose_language">
                    <input type="hidden" name="navigationWay" value="forward">
                    <input type="hidden" name="language" value="english">
                    <button type="submit">English</button>
                </form>
            </li>
            <li>
                <form action="<c:url value="/command"/>" method="post">
                    <input type="hidden" name="command" value="choose_language">
                    <input type="hidden" name="navigationWay" value="forward">
                    <input type="hidden" name="language" value="russian">
                    <button type="submit">Русский</button>
                </form>
            </li>
            <li>
                <form action="<c:url value="/command"/>" method="post">
                    <input type="hidden" name="command" value="choose_language">
                    <input type="hidden" name="navigationWay" value="forward">
                    <input type="hidden" name="language" value="belorussian">
                    <button type="submit">Беларуская</button>
                </form>
            </li>
        </ul>
    </li>
</ul>
<div class="inner-content">
    <div id="current-user">
        <fmx:message bundle="${topPanelB}" key="current.user.login"/> - ${user.login},
        <fmx:message bundle="${topPanelB}" key="current.user.role"/> -
        <c:choose>
            <c:when test="${user.role == 'ADMINISTRATOR'}">
                <fmx:message bundle="${topPanelB}" key="user.role.administrator"/>
            </c:when>
            <c:when test="${user.role == 'CLIENT'}">
                <fmx:message bundle="${topPanelB}" key="user.role.client"/>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
