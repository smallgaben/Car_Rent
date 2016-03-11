<%@include file="/view/jspf/taglibs.jspf"%>
<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div class="col-lg-offset-5 col-lg-2">
    <p class="h3" style="color: red">
        <fmt:message key="error.badOrder"/>
    </p>
    <a href="${pageContext.request.contextPath}/return">
        <button class="btn btn-primary"><fmt:message key="manager.button.return"/></button>
    </a>
</div>

</body>
</html>
