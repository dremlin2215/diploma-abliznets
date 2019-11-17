<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmx:setBundle basename="locale.client.bonuses_table_client" var="bonusesTableB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 15.06.2019
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${bonusesTableB}" key="title"/></title>
    <link href="<c:url value="/view/style/main.css"/>" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${bonusesTableB}" key="head"/></h1>
            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th><fmx:message bundle="${bonusesTableB}" key="table.head.name"/></th>
                        <th><fmx:message bundle="${bonusesTableB}" key="table.head.description"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="clientBonuses" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.Bonus>"--%>
                    <c:forEach items="${clientBonuses}" var="bonus">
                        <tr>
                            <td>${bonus.name}</td>
                            <td>${bonus.description}</td>
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
                               currentPageNumber="${currentPageNumber}" command="get_bonuses_client"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
