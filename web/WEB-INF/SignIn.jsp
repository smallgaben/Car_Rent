<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>

<div id="container">
    <h3>Signing</h3>
  <form method="post" action="${pageContext.request.contextPath}/signin" id="login_from">
      <div>
          <input type="text" name="username" required="" placeholder="Enter username" class="input">
      </div>
      <div>
          <input type="password" required="" name="password" placeholder="Enter password" class="input">
      </div>
    <button id="submit_button"  type="submit" name="login" >Sign In</button>
      <c:if test="${checked==false}">
          <div class="row">
              <p style="color: crimson; text-align: center">Please enter correct values!</p>
          </div>
      </c:if>
  </form>

    <h3>Registration</h3>
    <form method="post" action="${pageContext.request.contextPath}/register" id="register_form">
        <div>
            <input name="username" placeholder="Enter username" type="text" required="" class="input">
        </div>
        <div>
            <input name="password" placeholder="Enter password" type="password" required="" class="input">
        </div>
        <div>
            <input name="firstname" required="" placeholder="Enter First Name" class="input" type="text">
        </div>
        <div>
            <input name="lastname" required="" placeholder="Enter Last Name" class="input" type="text">
        </div>
        <button type="submit" name="register" id="register_button">Register</button>
        <c:if test="${registr==true}">
            <div class="row">
                <p style="color: green; text-align: center">User successfully registered</p>
            </div>
        </c:if>
    </form>
</div>

</body>
</html>
