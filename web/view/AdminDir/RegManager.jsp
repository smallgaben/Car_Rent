<%@include file="/view/jspf/taglibs.jspf" %>
<html>
<%@include file="/view/jspf/head.jspf" %>
<body>
<div class="col-lg-12 row center-block">
    <div class="col-lg-offset-5 col-lg-2">
        <h3><fmt:message key="registration"/></h3>

        <form method="post" action="${pageContext.request.contextPath}/addManager" class="form-group"
              id="register_form">
            <div>
                <input name="username" placeholder="<fmt:message key="enter.username"/>" type="text" required=""
                       class="form-control">
            </div>
            <div>
                <input name="password" placeholder="<fmt:message key="enter.password"/>" type="password" required=""
                       class="form-control">
            </div>
            <div>
                <input name="firstname" required="" placeholder="<fmt:message key="enter.firstName"/>"
                       class="form-control" type="text">
            </div>
            <div>
                <input name="lastname" required="" placeholder="<fmt:message key="enter.lastName"/>"
                       class="form-control" type="text">
            </div>
            <button type="submit" name="register" id="register_button" class="col-lg-12 btn btn-warning center-block">
                <fmt:message key="admin.button.regManager"/></button>
        </form>
    </div>
</div>

</body>
</html>
