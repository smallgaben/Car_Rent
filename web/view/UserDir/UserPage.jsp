<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="header-right">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" title="Logout"><i
                    class="btn-danger">Log Out</i></a>
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
                    <a href="${pageContext.request.contextPath}/carList"><i class="fa"></i>Car List</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/userOrders"><i class="fa"></i>My Orders</a>
                </li>
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

            <%--User Orders List--%>
            <%@include file="/view/jspf/userOrders.jspf" %>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<%@include file="/view/jspf/footer.jspf"%>
</body>
</html>
