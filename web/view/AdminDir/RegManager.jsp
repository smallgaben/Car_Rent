<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/view/jspf/head.jspf"%>
<body>
<div class="col-lg-12 row center-block">
  <div class="col-lg-offset-5 col-lg-2">
    <h3>Registration</h3>
    <form method="post" action="${pageContext.request.contextPath}/utilManager" class="form-group" id="register_form">
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
      <button type="submit" name="register" id="register_button" class="col-lg-12 btn btn-warning center-block">Register Manager</button>
    </form>
  </div>
</div>

</body>
</html>
