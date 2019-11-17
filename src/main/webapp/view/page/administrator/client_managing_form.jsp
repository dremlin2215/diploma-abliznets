<%--@elvariable id="client" type="com.minsk.BSU.abliznets.cafe.entitie.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.administrator.client_managing_form" var="clientManagingFormB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2019
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${clientManagingFormB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${clientManagingFormB}" key="head"/></h1>
            <div class="form-area">
                <div class="form-area-field">
                    <label for="client-name">
                        <fmx:message bundle="${clientManagingFormB}" key="client.name"/>
                    </label>
                    <input id="client-name" value="${client.name}" form="save-changes-form" readonly><br>
                </div>
                <div class="form-area-field">
                    <label for="client-surname">
                        <fmx:message bundle="${clientManagingFormB}" key="client.surname"/>
                    </label>
                    <input id="client-surname" value="${client.surname}" form="save-changes-form" readonly><br>
                </div>
                <div class="form-area-field">
                    <label for="client-login">
                        <fmx:message bundle="${clientManagingFormB}" key="client.login"/>
                    </label>
                    <input id="client-login" value="${client.login}" form="save-changes-form" readonly><br>
                </div>
                <div class="form-area-field">
                    <label for="client-score">
                        <fmx:message bundle="${clientManagingFormB}" key="client.score"/>
                    </label>
                    <input type="number" id="client-score" name="clientScore"
                           value="${client.score}" form="save-changes-form"><br>
                </div>
                <div class="form-area-field checkbox-field">
                    <label for="banned">
                        <fmx:message bundle="${clientManagingFormB}" key="client.is.banned"/>
                    </label>
                    <c:choose>
                        <c:when test="${client.banned}">
                            <input type="checkbox" id="banned" name="clientIsBanned" value="true"
                                   form="save-changes-form" checked><br>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="banned" name="clientIsBanned" value="true"
                                   form="save-changes-form"><br>
                        </c:otherwise>
                    </c:choose>
                </div>

                <button onclick="history.back()">
                    <fmx:message bundle="${clientManagingFormB}" key="button.cancel"/>
                </button>

                <form action="<c:url value="/command"/>" method="post" id="save-changes-form">
                    <input type="hidden" name="command" value="save_client_changes">
                    <input type="hidden" name="navigationWay" value="redirect">
                    <input type="hidden" name="clientID" value="${client.ID}">
                    <button type="submit">
                        <fmx:message bundle="${clientManagingFormB}"
                                     key="form.save.client.changes.button.save"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
