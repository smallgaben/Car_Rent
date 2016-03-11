<%@include file="/view/jspf/taglibs.jspf"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="controller.internationalization.i18n.lang" />

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
                    <input placeholder="<fmt:message key="enter.username"/>" id="s_username" type="text" name="username" required="" class="form-control">
                </div>
                <div>
                    <input placeholder="<fmt:message key="enter.password"/>" id="s_password" type="password" required="" name="password" class="form-control">
                </div>
                <button id="submit_button" class="col-lg-8 btn btn-primary center-block"  type="submit" name="login" ><fmt:message key="button.signin"/></button>
                <c:choose>
                <c:when test="${language=='ru_RU'}">
                    <input name="locale" value="ru" hidden/>
                </c:when>
                <c:otherwise>
                    <input name="locale" value="${language}"  hidden/>
                </c:otherwise>
            </c:choose>
                <c:if test="${checked==false}">
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
                    <input name="username" placeholder="<fmt:message key="enter.username"/>" type="text" required="" class="form-control">
                </div>
                <div>
                    <input name="password" placeholder="<fmt:message key="enter.password"/>" type="password" required="" class="form-control">
                </div>
                <div>
                    <input name="firstname" required="" placeholder="<fmt:message key="enter.firstName"/>" class="form-control" type="text">
                </div>
                <div>
                    <input name="lastname" required="" placeholder="<fmt:message key="enter.lastName"/>" class="form-control" type="text">
                </div>
                <button type="submit" name="register" id="register_button" class="col-lg-12 btn btn-success center-block"><fmt:message key="button.register"/></button>
                <c:choose>
                    <c:when test="${language=='ru_RU'}">
                        <input name="locale" value="ru" hidden/>
                    </c:when>
                    <c:otherwise>
                        <input name="locale" value="${language}"  hidden/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${exist==true}">
                    <p style="color: red"><fmt:message key="warn.userExist"/></p>
                </c:if>
            </form>
        </div>
    </div>

</body>
</html>
