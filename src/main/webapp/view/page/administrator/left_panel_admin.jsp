<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmx:setBundle basename="locale.administrator.left_panel_admin" var="leftPanelB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.04.2019
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${leftPanelB}" key="title"/></title>
</head>
<body>
<ul class="vertical-menu">
    <li>
        <form action="<c:url value="/command"/>">
            <input type="hidden" name="command" value="get_all_dishes">
            <input type="hidden" name="navigationWay" value="forward">
            <input type="hidden" name="pageNumber" value="1">
            <input type="hidden" name="recordsCount" value="5">
            <button type="submit">
                <fmx:message bundle="${leftPanelB}" key="form.get.all.dishes.button.dishes.managing"/>
            </button>
        </form><br>
    </li>
    <li>
        <form action="<c:url value="/command"/>">
            <input type="hidden" name="command" value="get_clients">
            <input type="hidden" name="navigationWay" value="forward">
            <input type="hidden" name="pageNumber" value="1">
            <input type="hidden" name="recordsCount" value="20">
            <button type="submit">
                <fmx:message bundle="${leftPanelB}" key="form.get.clients.button.clients.managing"/>
            </button>
        </form>
    </li>
</ul>
</body>
</html>
