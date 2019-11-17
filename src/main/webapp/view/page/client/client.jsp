<%--@elvariable id="user" type="com.minsk.BSU.abliznets.cafe.entitie.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setLocale value="${locale}" scope="session"/>
<fmx:setBundle basename="locale.client.client" var="clientB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 30.03.2019
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${clientB}" key="title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${clientB}" key="head"/>, ${user.login}!</h1>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>