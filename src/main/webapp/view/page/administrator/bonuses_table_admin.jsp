<%--@elvariable id="client" type="com.minsk.BSU.abliznets.cafe.entitie.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmx:setBundle basename="locale.administrator.bonuses_table_admin" var="bonusesTableB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 24.04.2019
  Time: 19:02
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
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1><fmx:message bundle="${bonusesTableB}" key="head"/> ${client.login}</h1>

            <div class="content-actions">
                <form action="<c:url value="/command"/>">
                    <input type="hidden" name="command" value="specify_new_bonus">
                    <input type="hidden" name="navigationWay" value="forward">
                    <button type="submit">
                        <fmx:message bundle="${bonusesTableB}"
                                     key="form.specify.new.bonus.button.add.new.bonus"/>
                    </button>
                </form>

                <form action="<c:url value="/command"/>">
                    <input type="hidden" name="command" value="get_clients">
                    <input type="hidden" name="navigationWay" value="forward">
                    <input type="hidden" name="pageNumber" value="1">
                    <input type="hidden" name="recordsCount" value="20">
                    <button type="submit">
                        <fmx:message bundle="${bonusesTableB}"
                                     key="form.get.clients.button.back.to.clients.table"/>
                    </button>
                </form>
            </div>

            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th><fmx:message bundle="${bonusesTableB}" key="table.head.name"/></th>
                        <th><fmx:message bundle="${bonusesTableB}" key="table.head.description"/></th>
                        <th><fmx:message bundle="${bonusesTableB}" key="table.head.delete.bonus"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="clientBonuses" type="java.util.List<com.minsk.BSU.abliznets.cafe.entitie.Bonus>"--%>
                    <c:forEach items="${clientBonuses}" var="bonus">
                        <tr>
                            <td>${bonus.name}</td>
                            <td>${bonus.description}</td>
                            <td>
                                <form action="<c:url value="/command"/>" method="post">
                                    <input type="hidden" name="command" value="delete_bonus">
                                    <input type="hidden" name="navigationWay" value="redirect">
                                    <input type="hidden" name="bonusID" value="${bonus.ID}">
                                    <button type="submit">
                                        <fmx:message bundle="${bonusesTableB}"
                                                     key="form.delete.bonus.button.delete"/>
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
                               currentPageNumber="${currentPageNumber}" command="get_bonuses_admin"/>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
