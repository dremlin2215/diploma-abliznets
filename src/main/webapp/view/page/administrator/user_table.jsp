<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmx:setBundle basename="locale.administrator.user_table" var="userTableB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 13.04.2019
  Time: 6:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${userTableB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${userTableB}" key="head"/></h1>
            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th><fmx:message bundle="${userTableB}" key="client.name"/></th>
                        <th><fmx:message bundle="${userTableB}" key="client.surname"/></th>
                        <th><fmx:message bundle="${userTableB}" key="client.login"/></th>
                        <th><fmx:message bundle="${userTableB}" key="client.score.number"/></th>
                        <th><fmx:message bundle="${userTableB}" key="client.is.banned"/></th>
                        <th><fmx:message bundle="${userTableB}" key="client.show.bonuses"/></th>
                        <th><fmx:message bundle="${userTableB}" key="client.manage.information"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="clients" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.user.User>"--%>
                    <c:forEach items="${clients}" var="client">
                        <tr>
                            <td>${client.name}</td>
                            <td>${client.surname}</td>
                            <td>${client.login}</td>
                            <td>${client.score}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${client.banned}">
                                        <fmx:message bundle="${userTableB}" key="is.banned.yes"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmx:message bundle="${userTableB}" key="is.banned.no"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="<c:url value="/command"/>">
                                    <input type="hidden" name="command" value="get_bonuses_admin">
                                    <input type="hidden" name="navigationWay" value="forward">
                                    <input type="hidden" name="pageNumber" value="1">
                                    <input type="hidden" name="recordsCount" value="20">
                                    <input type="hidden" name="clientID" value="${client.ID}">
                                    <input type="hidden" name="clientLogin" value="${client.login}">
                                    <button type="submit">
                                        <fmx:message bundle="${userTableB}"
                                                     key="form.get.bonuses.admin.button.show"/>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form action="<c:url value="/command"/>">
                                    <input type="hidden" name="command" value="manage_client_information">
                                    <input type="hidden" name="navigationWay" value="forward">
                                    <input type="hidden" name="clientID" value="${client.ID}">
                                    <button type="submit">
                                        <fmx:message bundle="${userTableB}"
                                                     key="form.manage.client.information"/>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="horizontal-centered">
                <%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
                <%--@elvariable id="pageCount" type="java.lang.Integer"--%>
                <%--@elvariable id="currentPageNumber" type="java.lang.Integer"--%>
                <ctg:paginator recordsCount="${recordsCount}" pageCount="${pageCount}"
                               currentPageNumber="${currentPageNumber}" command="get_clients"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
