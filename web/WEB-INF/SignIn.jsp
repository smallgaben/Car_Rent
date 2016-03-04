<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="/view/jspf/head.jspf"%>
<body>

    <div class="col-lg-12 row center-block">
        <div class="col-lg-offset-5 col-lg-2">
            <h3>Signing</h3>
            <form class="form-group" method="post" action="${pageContext.request.contextPath}/signin">
                <div>
                    <input type="text" name="username" required="" placeholder="Enter username" class="form-control">
                </div>
                <div>
                    <input type="password" required="" name="password" placeholder="Enter password" class="form-control">
                </div>
                <button id="submit_button" class="col-lg-12 btn btn-primary center-block"  type="submit" name="login" >Sign In</button>
                <c:if test="${checked==true}">
                    <div class="row">
                        <p style="color: crimson; text-align: center">Uncorrect values or blocked user!</p>
                    </div>
                </c:if>
            </form>
        </div>
    </div>

    <div class="col-lg-12 row center-block">
        <div class="col-lg-offset-5 col-lg-2">
            <h3>Registration</h3>
            <form method="post" action="${pageContext.request.contextPath}/register" class="form-group" id="register_form">
                <div>
                    <input name="username" placeholder="Enter username" type="text" required="" class="form-control">
                </div>
                <div>
                    <input name="password" placeholder="Enter password" type="password" required="" class="form-control">
                </div>
                <div>
                    <input name="firstname" required="" placeholder="Enter First Name" class="form-control" type="text">
                </div>
                <div>
                    <input name="lastname" required="" placeholder="Enter Last Name" class="form-control" type="text">
                </div>
                <button type="submit" name="register" id="register_button" class="col-lg-12 btn btn-success center-block">Register</button>
            </form>
        </div>
    </div>

</body>
</html>
