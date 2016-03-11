<%@include file="/view/jspf/taglibs.jspf"%>
<html>
<%@ include file="/view/jspf/head.jspf" %>
<body>
<div id="wrapper">
  <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
    <div class="header-right">
      <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" title="Logout"><i class="btn-danger"><fmt:message key="button.logout"/></i></a>
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
          <a  href="${pageContext.request.contextPath}/orderList?newOrders=true"><i class="fa"></i><fmt:message key="manager.newOrders"/></a>
        </li>
        <li>
          <a  href="${pageContext.request.contextPath}/orderList"><i class="fa"></i><fmt:message key="manager.orderList"/></a>
        </li>
      </ul>
    </div>

  </nav>
  <!-- /. NAV SIDE  -->
  <div id="page-wrapper">
    <div id="page-inner">

      <c:choose>
        <c:when test="${newOrders}">
          <%@include file="/view/jspf/managerNewOrders.jspf"%>
        </c:when>
        <c:otherwise>
          <%@include file="/view/jspf/managerOrderList.jspf"%>
        </c:otherwise>
      </c:choose>

    </div>
    <!-- /. PAGE INNER  -->
  </div>
  <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<%--FOOTER--%>
<%@include file="/view/jspf/footer.jspf"%>
</body>
</html>
