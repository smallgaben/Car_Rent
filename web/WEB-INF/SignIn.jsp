<%@include file="/view/jspf/taglibs.jspf"%>

<html>
<%@include file="/view/jspf/head.jspf"%>
<body>
<div class="col-lg-1">
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select>
    </form>
</div>

    <div class="col-lg-12 row center-block">
        <div class="col-lg-offset-5 col-lg-2">
            <h3><fmt:message key="signing"/></h3>
            <form class="form-group" method="post" action="${pageContext.request.contextPath}/signin">
                <div>
                    <label for="s_username"><fmt:message key="enter.username"/></label>
                    <input id="s_username" type="text" name="username" required="" class="form-control">
                </div>
                <div>
                    <label for="s_password"><fmt:message key="enter.password"/></label>
                    <input id="s_password" type="password" required="" name="password" class="form-control">
                </div>
                <button id="submit_button" class="col-lg-12 btn btn-primary center-block"  type="submit" name="login" ><fmt:message key="button.signin"/></button>
                <c:if test="${checked==true}">
                    <div class="row">
                        <p style="color: crimson; text-align: center"><fmt:message key="warn.badValues"/></p>
                    </div>
                </c:if>
            </form>
        </div>
    </div>

    <div class="col-lg-12 row center-block">
        <div class="col-lg-offset-5 col-lg-2">
            <h3><fmt:message key="registration"/></h3>
            <form method="post" action="${pageContext.request.contextPath}/register" class="form-group" id="register_form">
                <div>
                    <label for="r_username"><fmt:message key="enter.username"/></label>
                    <input id="r_username" name="username" type="text" required="" class="form-control">
                </div>
                <div>
                    <label for="r_password"><fmt:message key="enter.password"/></label>
                    <input id="r_password" name="password" type="password" required="" class="form-control">
                </div>
                <div>
                    <label for="r_firstname"><fmt:message key="enter.firstName"/></label>
                    <input id="r_firstname" name="firstname" required="" class="form-control" type="text">
                </div>
                <div>
                    <label for="r_lastname"><fmt:message key="enter.lastName"/></label>
                    <input id="r_lastname" name="lastname" required="" class="form-control" type="text">
                </div>
                <button type="submit" name="register" id="register_button" class="col-lg-12 btn btn-success center-block"><fmt:message key="button.register"/></button>
                <c:if test="${exist==true}">
                    <p style="color: red"><fmt:message key="warn.userExist"/></p>
                </c:if>
            </form>
        </div>
    </div>

</body>
</html>
