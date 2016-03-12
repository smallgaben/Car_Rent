<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="controller.internationalization.i18n.lang" />

<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div id="wrapper">

    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="header-right">
                <c:choose>
                    <c:when test="${empty sessionScope.role}">
                        <div class="col-lg-4">
                            <form class="form-group">
                                <select id="language" class="form-control" name="language" onchange="submit()">
                                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                                </select>
                            </form>
                        </div>
                        <div class="col-lg-8">
                            <form class="form-inline" method="post" action="${pageContext.request.contextPath}/signin">
                                <c:choose>
                                    <c:when test="${language=='ru_RU'}">
                                        <input  name="locale" value="ru" hidden/>
                                    </c:when>
                                    <c:otherwise>
                                        <input  name="locale" value="${language}" hidden/>
                                    </c:otherwise>
                                </c:choose>
                                    <input class="form-control" placeholder="<fmt:message key="enter.username"/>" type="text" name="username"
                                           required="">
                                    <input class="form-control" placeholder="<fmt:message key="enter.password"/>" type="password" required=""
                                           name="password">

                                <button type="submit" class="btn btn-primary"><fmt:message key="button.signin"/></button>
                            </form>
                            <div>
                                <a href="${pageContext.request.contextPath}/view/Registration/RegisterPage.jsp">
                                    <button class="btn btn-warning"><fmt:message key="button.register"/></button>
                                </a>
                            </div>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" title="Logout"><i
                                class="btn-danger"><fmt:message key="button.logout"/></i></a>
                    </c:otherwise>
                </c:choose>

        </div>
    </nav>
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li>
                    <div class="user-img-div">
                        <div class="inner-text">
                            <p>${username}</p>
                        </div>
                    </div>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/carList"><i class="fa"></i><fmt:message key="admin.carlist"/></a>
                </li>
                <c:if test="${not empty sessionScope.role}">
                    <li>
                        <a href="${pageContext.request.contextPath}/userOrders"><i class="fa"></i><fmt:message key="user.myOrders"/></a>
                    </li>
                </c:if>
            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <%--Filters for navigation--%>
        <%@include file="/view/jspf/userFilters.jspf" %>
        <div id="page-inner">

            <%--Cars List--%>
            <%@include file="/view/jspf/carList.jspf" %>

            <c:if test="${not empty sessionScope.role}">
                <%--User Orders List--%>
                <%@include file="/view/jspf/userOrders.jspf" %>
            </c:if>

        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<%@include file="/view/jspf/footer.jspf"%>
</body>
</html>
