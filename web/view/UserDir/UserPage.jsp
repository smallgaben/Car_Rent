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
                    <a  href="${pageContext.request.contextPath}/CarList"><i class="fa"></i>Car List</a>
                </li>
                <li>
                    <a  href="/UserCars"><i class="fa"></i>My Cars</a>
                </li>
                <li>
                    <a  href="/about"><i class="fa"></i>ABOUT</a>
                </li>
            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <c:forEach var="car" items="${cars}">
                <div class="row">
                    <div class="col-md-12 bg-info">
                        <div class="col-md-2">
                            Car mark: ${car.mark}

                        </div>
                        <div class="col-md-2">
                            Car name: ${car.name}

                        </div>
                        <div class="col-md-2">
                            Car cost: ${car.cost}
                        </div>
                        <div class="col-md-2">
                            Car class: ${car.carClass.name}
                        </div>
                        <div class="col-md-2">
                            Car status: ${car.status.name}
                        </div>
                        <div class="col-md-2">
                            <a href="/RentCar">
                                <button name="rent" value="${car.id}" class="btn-warning" >Rent</button>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
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
