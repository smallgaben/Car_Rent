<%@include file="/view/jspf/taglibs.jspf" %>

<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="header-right">
            <a href="${pageContext.request.contextPath}/view/AdminDir/RegManager.jsp"><i
                    class="btn btn-warning"><fmt:message key="admin.button.regManager"/></i></a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" title="Logout"><i
                    class="btn-danger"><fmt:message key="button.logout"/></i></a>
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
                    <a href="${pageContext.request.contextPath}/carList"><i class="fa"></i><fmt:message
                            key="admin.carlist"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/managerList"><i class="fa"></i><fmt:message
                            key="admin.managersList"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/userList"><i class="fa"></i><fmt:message
                            key="admin.usersList"/></a>
                </li>
            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div class="panel-group" id="page-inner">
            <c:if test="${cars!=null}">
                <div class="panel-body row">
                    <form method="post" action="${pageContext.request.contextPath}/addCar" class="form-group inline">
                        <div class="col-md-2">
                            <label for="mark"><fmt:message key="car.mark"/>: </label>
                            <input id="mark" type="text" required="" class="form-control" name="mark">
                        </div>
                        <div class="col-md-2">
                            <label for="name"><fmt:message key="car.name"/>: </label>
                            <input class="form-control" id="name" required="" name="name"/>
                        </div>
                        <div class="col-md-2">
                            <label for="cost"><fmt:message key="car.cost"/>: </label>
                            <input id="cost" name="cost" class="form-control" required=""/>
                        </div>
                        <div class="col-md-2">
                            <label for="class"><fmt:message key="car.carClass"/>: </label>
                            <input id="class" name="class" class="form-control" required=""/>
                        </div>
                        <div class="col-md-2 list-inline">
                            <button type="submit" class="btn btn-info"><fmt:message key="admin.button.addCar"/></button>
                        </div>
                    </form>
                </div>
            </c:if>

            <%--List all Cars--%>
            <%@include file="/view/jspf/carInputList.jspf" %>

            <%--List all Users--%>
            <%@include file="/view/jspf/userList.jspf" %>

            <%--List all Managers--%>
            <%@include file="/view/jspf/managerList.jspf" %>

        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->

<%@include file="/view/jspf/footer.jspf" %>

</body>
</html>
