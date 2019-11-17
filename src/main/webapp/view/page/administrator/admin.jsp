<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmx:setLocale value="${locale}" scope="session"/>
<fmx:setBundle basename="locale.administrator.admin" var="adminB"/>
<%--@elvariable id="user" type="com.minsk.BSU.abliznets.cafe.entitie.user.User"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 30.03.2019
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${adminB}" key="title"/></title>
    <link rel="stylesheet" href="<c:url value="/view/style/main.css"/>">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${adminB}" key="head"/>, ${user.login}!</h1>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
