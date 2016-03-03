<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="header-right">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" title="Logout"><i class="btn-danger">Log Out</i></a>
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
                    <a href="${pageContext.request.contextPath}/carList"><i class="fa"></i>Cars List</a>
                </li>
                <li>
                    <a  href="/managersList"><i class="fa"></i>Managers List</a>
                </li>
                <li>
                    <a  href="${pageContext.request.contextPath}/userList"><i class="fa"></i>Users List</a>
                </li>
            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div class="panel-group" id="page-inner">
            <div class="panel-body row">
                <form action="${pageContext.request.contextPath}/addCar" class="form-group inline">
                    <div class="col-md-2">
                        <label for="mark">Mark: </label>
                        <input id="mark" type="text" class="form-control" name="mark">
                    </div>
                    <div class="col-md-2">
                        <label for="name">Name: </label>
                        <input class="form-control" id="name" name="name" />
                    </div>
                    <div class="col-md-2">
                        <label for="cost">Cost</label>
                        <input id="cost" name="cost" class="form-control" />
                    </div>
                    <div class="col-md-2">
                            <label for="class">Class: </label>
                            <input id="class" name="class" class="form-control" />
                    </div>
                    <div class="col-md-2 list-inline">
                        <button type="submit" class="btn btn-info">Add Car</button>
                    </div>
                </form>
            </div>

            <%--List all Cars--%>
            <%@include file="/view/jspf/carList.jspf"%>

            <%--List all Users--%>
            <%@include file="/view/jspf/userList.jspf"%>

        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<div id="footer-sec">
    2016 PrutOS | Design By : Miroslav
</div>

</body>
</html>
