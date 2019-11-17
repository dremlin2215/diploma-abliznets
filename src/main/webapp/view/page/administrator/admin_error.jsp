<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.administrator.admin_error" var="adminErrorB"/>
<%--@elvariable id="errorMessage" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 15.06.2019
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${adminErrorB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <div class="centered">
                <h1>${errorMessage}</h1>
                <button onclick="history.back()">
                    <fmx:message bundle="${adminErrorB}" key="button.back.to.previous.page"/>
                </button>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
