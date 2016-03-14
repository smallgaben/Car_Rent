<%@include file="/view/jspf/taglibs.jspf" %>
<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div class="col-lg-offset-4 col-lg-4">
    <div id="container" class="clear">

        <div class="hgroup clear">
            <p class="h1">404</p>

            <p class="h2"><fmt:message key="error"/> <span><fmt:message key="error.notFound"/></span></p>
        </div>
        <p><fmt:message key="error.info"/></p>

        <p><a href="${pageContext.request.contextPath}/return"><fmt:message key="manager.button.return"/></a></p>
    </div>
</div>
</body>

</html>
